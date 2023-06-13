package com.leonel.intercamlp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.leonel.intercamlp.MainActivity
import com.leonel.intercamlp.R
import com.leonel.intercamlp.databinding.ActivityLoginBinding
import com.leonel.intercamlp.model.user
import com.leonel.intercamlp.utils.Constant
import com.leonel.intercamlp.utils.CustomDialog
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    var userSave= user("","")
    private val viewModel: LoginActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intiUser()
        binding.btnLogin.setOnClickListener {

            if(viewModel.validateInfo(binding.etxtUser.text.toString(),binding.etxtPassword.text.toString())) {
                userSave.user = binding.etxtUser.text.toString().trim()
                userSave.password = binding.etxtPassword.text.toString()
                viewModel.onConsultUser(userSave)
            }
        }

        viewModel.isLoading.observe(this ){
            if(it){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else
                viewModel.alertvalidation(this)
        }

        viewModel.passError.observe(this){
            binding.etxtPassword.setError(getString(R.string.txt_setError))
        }
        viewModel.userError.observe(this){
            binding.etxtUser.setError(getString(R.string.txt_setError))
        }

    }

    private fun intiUser(){
        userSave = user("","")
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        val inicializado = sharedPref.getBoolean(Constant.INITSHARED, false)
        if(!inicializado){
            userSave = user(Constant.USER,Constant.PASSWORD)
            viewModel.onCreateUser(userSave)
            with (sharedPref.edit()) {
                putBoolean(Constant.INITSHARED, true)
                apply()
            }
        }
    }




}