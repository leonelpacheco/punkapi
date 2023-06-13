package com.leonel.intercamlp.ui.login

import android.content.Context
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonel.intercamlp.R
import com.leonel.intercamlp.database.entities.toDataBase
import com.leonel.intercamlp.model.user
import com.leonel.intercamlp.repository.userRepository
import com.leonel.intercamlp.utils.CustomDialog
import com.leonel.intercamlp.utils.StringResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LoginActivityViewModel @Inject constructor(private val repository: userRepository, private val stringResourcesProvider: StringResourcesProvider): ViewModel() {


    val listuserModel = MutableLiveData<List<user>>()
    val isLoading = MutableLiveData<Boolean>()
    var userError = MutableLiveData<String>()
    var passError = MutableLiveData<String>()

    fun onCreateUser(userinsert: user){

        viewModelScope.launch {
            val result= invoke(userinsert)
            if (!result.isNullOrEmpty()) {
                listuserModel.postValue(result!!)
            }

        }

    }

    fun onConsultUser(userconsult: user){

        viewModelScope.launch {
            val result= invokeuserconsult(userconsult)
            if (!result.isNullOrEmpty()) {
                listuserModel.postValue(result!!)
                isLoading.postValue(true)
            }
            else
                isLoading.postValue(false)
        }

    }

    //*** uses cases****
    suspend  fun invokeuserconsult(user : user):List<user>{
        user.password = encode(user.password)
        return repository.getConsultUsesrsFromDatabase(user)
    }

    suspend operator fun invoke(userinsert : user):List<user>{
        userinsert.password = encode(userinsert.password)
        repository.insertUser(userinsert.toDataBase())
        return repository.getAllUsesrsFromDatabase()
    }

    fun encode(pass : String): String{
        var encoded = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val encoder: Base64.Encoder = Base64.getEncoder()
            encoded = encoder.encodeToString(pass.toByteArray())
        }
        return encoded
    }
    fun decode(pass : String): String{
        var decoded = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val decoder: Base64.Decoder = Base64.getDecoder()
            decoded = String(decoder.decode(pass))
        }
        return decoded
    }

    fun validateInfo(user: String, password: String):Boolean{
        var validate = true
        if(user.isNullOrBlank()){
            userError.postValue(stringResourcesProvider.getString(R.string.txt_setError))
            validate= false
        }
        if(password.isNullOrBlank()){
            passError.postValue(stringResourcesProvider.getString(R.string.txt_setError))

            validate= false
        }
        return validate
    }

    fun alertvalidation(context: Context){
        val customDialog = CustomDialog(context)
        customDialog.one(
            description = stringResourcesProvider.getString(R.string.login_dialog_error),
            titleOfPositiveButton = stringResourcesProvider.getString(R.string.dialog_btn_accept),
            positiveButtonFunction = {

            }).show()
    }




}