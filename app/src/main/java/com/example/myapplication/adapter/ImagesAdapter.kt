package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.myapplication.DetailActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ImagesLayoutBinding
import com.example.myapplication.databinding.VideoListLayoutBinding
import com.example.myapplication.models.Result


class ImagesAdapter(private val data: List<String>, appContext: Context) :
    RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {
     var adapterContext: Context

    init {
        adapterContext = appContext
    }

    class ViewHolder(val binding: ImagesLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ImagesLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load("https:" + data[position])
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {

                    holder.binding.img.setImageDrawable(
                        ContextCompat.getDrawable(
                            adapterContext,
                            R.drawable.ic_launcher_background
                        )
                    )
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return true
                }

            })
            .placeholder(R.drawable.ic_launcher_background)


            .into(holder.binding.img)


    }

    override fun getItemCount(): Int {
        return data.size
    }

}