package com.nexis.ders_25_webserivce.service

import com.nexis.anbar.data.model.tblQeydiyyat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users")
    fun getFriends(): Call<List<tblQeydiyyat?>>

    @GET("users/{userId}")
    fun getFriendById(@Path("userId") userId: String): Call<tblQeydiyyat?>
}