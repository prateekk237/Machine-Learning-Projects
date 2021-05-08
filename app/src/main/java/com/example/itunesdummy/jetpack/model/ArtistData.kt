package com.example.itunesdummy.jetpack.model
import com.google.gson.annotations.SerializedName

data class ArtistData(
    @SerializedName("results")
     val dataList:List<Artist>
)