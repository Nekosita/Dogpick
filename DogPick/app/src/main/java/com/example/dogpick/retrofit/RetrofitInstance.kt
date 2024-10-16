package com.example.dogpick.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
     val api: dogApi by lazy {
         Retrofit.Builder()
             .baseUrl("https://dog.ceo/api/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(dogApi::class.java)
     }
}