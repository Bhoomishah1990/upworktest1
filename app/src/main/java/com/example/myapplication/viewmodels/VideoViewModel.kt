package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.apis.RetrofitInstance
import com.example.myapplication.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoViewModel : ViewModel() {
    private var videoLiveData = MutableLiveData<List<Result>>()
    fun getPopularVideos() {
        RetrofitInstance.api.getPopularVideos("1").enqueue(object  : Callback<List<Result>>{
            override fun onResponse(call: Call<List<Result>>, response: Response<List<Result>>) {
                if (response.body()!=null){
                    videoLiveData.value = response.body()!!
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<List<Result>>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeVideoLiveData() : LiveData<List<Result>> {
        return videoLiveData
    }
}