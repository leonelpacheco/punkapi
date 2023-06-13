package com.leonel.intercamlp.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonel.intercamlp.database.entities.toDataBase
import com.leonel.intercamlp.model.Beer
import com.leonel.intercamlp.model.favorito
import com.leonel.intercamlp.repository.beerrepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val beerrepository: beerrepository): ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val beersLiveData = MutableLiveData<List<favorito>>()

    fun getBeersData(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result= ContularBeersdata()
            if (!result.isNullOrEmpty()) {
                beersLiveData.postValue(result)
                isLoading.postValue(false)
            }
            else
                isLoading.postValue(false)
        }

    }


    fun updateRatingData(favorite:favorito){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result= updateRatingFavoritesdata(favorite)
            if (result) {
                isLoading.postValue(false)
            }
            else
                isLoading.postValue(false)
        }

    }

    //************USES CASES********************
    suspend fun ContularBeersdata(): List<favorito>{
        return beerrepository.getConsultFavoritesFromDatabase()

    }

   suspend fun updateRatingFavoritesdata(favorite:favorito): Boolean{
        beerrepository.updateRatingFavoritos(favorite.toDataBase())
        return true
    }
    
}