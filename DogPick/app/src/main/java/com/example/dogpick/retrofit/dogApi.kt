package com.example.dogpick.retrofit

import com.example.dogpick.data.dogData
import com.example.dogpick.data.dogDataS
import retrofit2.Call
import retrofit2.http.GET

interface dogApi {

    @GET("breeds/image/random")
    fun getRandomDogData(): Call<dogData>

    @GET("breeds/image/random/3")
    fun getDogDataS(): Call <dogDataS>
}