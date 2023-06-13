package com.leonel.intercamlp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leonel.intercamlp.model.favorito
import com.leonel.intercamlp.model.user


@Entity(tableName = "favoritos_table")
data class favoritosEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "tagline") val tagline: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "rating") val rating: Int = 0

)

fun favorito.toDataBase() = favoritosEntity(id=id,name= name, tagline = tagline, image=image, rating=rating)