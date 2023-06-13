package com.leonel.intercamlp.database.dao

import androidx.room.*
import com.leonel.intercamlp.database.entities.favoritosEntity
import com.leonel.intercamlp.database.entities.userEntity


@Dao
interface favoritosDao {
    @Query("SELECT * FROM favoritos_table ORDER BY id DESC")
    suspend fun getAllfavoritos():List<favoritosEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(favoritos:favoritosEntity)

    @Query("DELETE FROM favoritos_table")
    suspend fun deleteAllfavoritos()


    @Update
    suspend fun updateRating(favoritos:favoritosEntity)

}