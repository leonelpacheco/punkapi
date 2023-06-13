package com.leonel.intercamlp.database.dao

import androidx.room.*
import com.leonel.intercamlp.database.entities.userEntity
import com.leonel.intercamlp.model.user


@Dao
interface userDao {
    @Query("SELECT * FROM user_table ORDER BY id DESC")
    suspend fun getAllUsers():List<userEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user:userEntity)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table WHERE user = :user AND password = :pass")
    suspend fun getConsultUser(user: String, pass: String):List<userEntity>


}