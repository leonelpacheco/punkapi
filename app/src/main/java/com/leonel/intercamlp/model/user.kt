package com.leonel.intercamlp.model


import com.google.gson.annotations.SerializedName
import com.leonel.intercamlp.database.entities.userEntity

data class user (
    @SerializedName("user") var user: String,
    @SerializedName("password") var password: String
)
fun user.add()=user(user,password)
fun userEntity.add()=user(user,password)