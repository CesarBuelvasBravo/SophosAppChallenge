package com.example.myapplication.model.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    object RetrofitHelper {
        fun getRetroFit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://6w33tkx4f9.execute-api.us-east-1.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}