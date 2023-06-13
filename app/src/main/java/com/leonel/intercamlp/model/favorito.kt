package com.leonel.intercamlp.model


import com.google.gson.annotations.SerializedName
import com.leonel.intercamlp.database.entities.favoritosEntity
import com.leonel.intercamlp.database.entities.userEntity

data class favorito (
                @SerializedName("id") val id: Long,
                 @SerializedName("name") val name: String,
                 @SerializedName("tagline") val tagline: String,
                 @SerializedName("image") val image: String,
                 @SerializedName("rating") var rating: Int
)
fun favorito.add()=favorito(id,name,tagline,image,rating)
fun favoritosEntity.add()=favorito(id,name,tagline,image,rating)