package com.example.itunesdummy.jetpack.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.itunesdummy.jetpack.model.Artist
import com.example.itunesdummy.jetpack.model.ArtistData
import com.example.itunesdummy.jetpack.room.ArtistDB
import com.example.itunesdummy.utils.RetrofitService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ArtistRepo {
    companion object{
        private var artistRepo:ArtistRepo? = null
        fun getRepoInstance():ArtistRepo?{
            if(artistRepo==null)
                artistRepo = ArtistRepo()
            return artistRepo
        }
    }
    private val artistDao = ArtistDB.getInstance()!!.artistDao()
    fun getArtistData(name:String): LiveData<List<Artist>> = artistDao.getArtistData(name)

    fun insertData(dataList:List<Artist>){
        object : Thread(){
            override fun run() {
                super.run()
                artistDao.insertArtistData(dataList)
            }
        }.start()

    }
    fun getDataFromInternet(term : String) : MutableLiveData<List<Artist>>{
        val artistData:MutableLiveData<List<Artist>> = MutableLiveData()
        RetrofitService.createService().getArtistData(term).enqueue(object :  retrofit2.Callback<ArtistData> {
            override fun onFailure(call: Call<ArtistData>?, t: Throwable?) {
                t!!.message?.let { Log.e("error : ", it) }
            }

            override fun onResponse(call: Call<ArtistData>?, response: Response<ArtistData>?) {
                if(response!!.isSuccessful){
                    response.body()!!.dataList[0].artistName?.let { Log.e("data ", it) }
                    artistData.value = response.body()!!.dataList
                }
            }

        })
        return artistData
    }
}