package com.nexis.ders_25_webserivce.Helper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun retrofitBuilder(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl("https://dbanbaryeni-b2572-default-rtdb.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}