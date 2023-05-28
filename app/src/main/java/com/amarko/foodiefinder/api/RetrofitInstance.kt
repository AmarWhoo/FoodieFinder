package com.amarko.foodiefinder.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.spoonacular.com/"
    const val API_KEY = "882dfc11ed54406a9907b0414ab1e396"

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SpoonApi by lazy {
        retrofit.create(SpoonApi::class.java)
    }
}