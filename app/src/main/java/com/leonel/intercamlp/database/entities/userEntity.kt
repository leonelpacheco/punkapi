package com.leonel.intercamlp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leonel.intercamlp.model.user


@Entity(tableName = "user_table")
data class userEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "user") val user: String,
    @ColumnInfo(name = "password") val password: String,

)

fun user.toDataBase() = userEntity(user= user, password = password)