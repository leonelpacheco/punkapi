package com.leonel.intercamlp.ui.beers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonel.intercamlp.database.entities.toDataBase
import com.leonel.intercamlp.model.Beer
import com.leonel.intercamlp.model.favorito
import com.leonel.intercamlp.repository.beerrepository
import com.leonel.intercamlp.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(private val beerrepository: beerrepository): ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val beersLiveData = MutableLiveData<List<Beer>>()

    fun getBeersData(page: Int){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result= ContularBeersdata(page)
            if (!result.isNullOrEmpty()) {
                beersLiveData.value =  beersLiveData.value?.plus(result) ?: result
                beersLiveData.postValue(beersLiveData.value)
                isLoading.postValue(false)
            }
            else
                isLoading.postValue(false)
        }

    }

    fun getSaveFavorites(beer: Beer){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result= saveFavorites(beer)
            if (result) {
                isLoading.postValue(false)
            }
            else
                isLoading.postValue(false)
        }

    }


    //************USES CASES********************
    suspend fun ContularBeersdata(page: Int): List<Beer>{
        return beerrepository.getBeers(page,Constant.PERPAGE )
    }

    suspend  fun saveFavorites(beer: Beer):Boolean {

        val favorite= favorito(beer.id,beer.name,beer.tagline,beer.imageURL,0)
        beerrepository.inserFavoritos(favorite.toDataBase())
        return true
    }
    
}