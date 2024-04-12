package com.example.myapplication.models

import android.content.Intent
import android.os.Build
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Result(
    @SerializedName("title") var title: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("upload_time") var upload_time: String? = null,
    @SerializedName("video_time") var video_time: String? = null,
    @SerializedName("star_name") var star_name: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("video_url") var video_url: String? = null,
    @SerializedName("main_thumb") var main_thumb: String? = null,
    @SerializedName("image_thumb") var image_thumb: String? = null,

    @SerializedName("id") var id: String? = null
): Serializable


fun <T : Serializable?> Intent.getSerializable(key: String, m_class: Class<T>): T {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        this.getSerializableExtra(key, m_class)!!
    else
        this.getSerializableExtra(key) as T
}