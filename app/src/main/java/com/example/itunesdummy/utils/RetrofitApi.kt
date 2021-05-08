package com.example.itunesdummy.utils

import com.example.itunesdummy.jetpack.model.ArtistData
import com.example.itunesdummy.jetpack.room.ArtistDB
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("search")
     fun getArtistData(@Query("term") term:String): Call<ArtistData>
}