package com.example.myapplication.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginService {
    @GET("/RS_Usuarios")
    suspend fun doLogin(
        @Query("idUsuario") userID: String,
        @Query("clave") password: String
    )
            : Response<LoginResponse>
}