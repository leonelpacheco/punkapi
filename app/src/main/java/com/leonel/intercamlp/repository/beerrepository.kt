package com.leonel.intercamlp.repository

import com.leonel.intercamlp.database.dao.favoritosDao
import com.leonel.intercamlp.database.dao.userDao
import com.leonel.intercamlp.database.entities.favoritosEntity
import com.leonel.intercamlp.database.entities.userEntity
import com.leonel.intercamlp.model.Beer
import com.leonel.intercamlp.model.add
import com.leonel.intercamlp.model.favorito
import com.leonel.intercamlp.model.user
import com.leonel.intercamlp.network.service
import javax.inject.Inject

class beerrepository  @Inject constructor(private val api: service, val favoritosDao: favoritosDao){

    suspend fun getBeers(page: Int, per_page: String): List<Beer> {
        val response: List<Beer> = api.getBeers(page,per_page)
        return response
    }

    suspend fun getConsultFavoritesFromDatabase():List<favorito>{
        val response: List<favoritosEntity> = favoritosDao.getAllfavoritos()
        return response.map { it.add() }
    }

    suspend fun inserFavoritos(favorito:favoritosEntity){
        favoritosDao.insertAll(favorito)
    }

    suspend fun updateRatingFavoritos(favorito:favoritosEntity){
        favoritosDao.updateRating(favorito)
    }
}