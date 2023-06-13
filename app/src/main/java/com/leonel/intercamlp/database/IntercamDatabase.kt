package com.leonel.intercamlp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leonel.intercamlp.database.dao.favoritosDao
import com.leonel.intercamlp.database.dao.userDao
import com.leonel.intercamlp.database.entities.favoritosEntity
import com.leonel.intercamlp.database.entities.userEntity

@Database(entities = [favoritosEntity::class, userEntity::class], version = 1)
abstract class IntercamDatabase: RoomDatabase()  {
    abstract fun getFavoritosDao(): favoritosDao
    abstract fun getUserDao(): userDao
}