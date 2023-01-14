package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("id") val id: String,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apellido") val apellido: String,
    @SerializedName("acceso") val acceso: Boolean,
    @SerializedName("admin") val admin: Boolean

)

