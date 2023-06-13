package com.leonel.intercamlp.network


import com.leonel.intercamlp.model.Beer
import com.leonel.intercamlp.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface apiClient {
    @GET(Constant.PAGE)
    suspend fun getBeers(@Query("page")  page: Int, @Query("per_page") per_page: String): Response<List<Beer>>

/*    @GET(Constant.QUERYMOVIMIENTOS)
    suspend fun getMovimientos(): Response<responseMovimientos>*/

}