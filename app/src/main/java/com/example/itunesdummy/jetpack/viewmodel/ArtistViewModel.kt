package com.example.itunesdummy.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.itunesdummy.jetpack.model.Artist
import com.example.itunesdummy.jetpack.repo.ArtistRepo

class ArtistViewModel : ViewModel() {
    private val artistRepo = ArtistRepo.getRepoInstance()
    fun getArtistData(name:String): LiveData<List<Artist>> = artistRepo!!.getArtistData(name)
    fun insertData(dataList:List<Artist>){
        artistRepo!!.insertData(dataList)
    }
    fun getDataFromInternet(term:String):MutableLiveData<List<Artist>> = artistRepo!!.getDataFromInternet(term)
}