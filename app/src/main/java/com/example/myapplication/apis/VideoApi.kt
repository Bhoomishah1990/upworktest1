package com.example.myapplication.apis

import com.example.myapplication.models.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoApi {

    @GET("videos?/per_page=10")
    fun getPopularVideos(@Query("page") page:String) : Call<List<Result>>
}