package com.leonel.intercamlp.network

import com.leonel.intercamlp.model.Beer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class service @Inject constructor(private val api:apiClient) {

    suspend fun getBeers(page: Int, per_page: String): List<Beer> {
        return withContext(Dispatchers.IO) {
            val response = api.getBeers(page, per_page)
            response.body() ?: emptyList()
        }
    }

/*    suspend fun getMovimientos(): List<Movimientos> {
        return withContext(Dispatchers.IO) {
            val response = api.getMovimientos()
            response.body()?.result ?: emptyList()
        }
    }*/
}