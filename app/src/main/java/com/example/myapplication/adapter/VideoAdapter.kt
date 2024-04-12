package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
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
import com.example.myapplication.databinding.VideoListLayoutBinding
import com.example.myapplication.models.Result
import com.example.myapplication.ui.theme.utils


class VideoAdapter(appcontext: Context) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {
    private var videoList = ArrayList<Result>()
    var adapterContext: Context

    init {
        adapterContext = appcontext
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setVideoList(videoList: List<Result>) {
        this.videoList = videoList as ArrayList<Result>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: VideoListLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            VideoListLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load("https:" + videoList[position].main_thumb)
            .placeholder(R.drawable.ic_launcher_background)

            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.binding.videoImage.setImageDrawable(
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
                    return false
                }

            })

            .into(holder.binding.videoImage)


        holder.binding.videoName.text = videoList[position].title

        holder.binding.cardView.setOnClickListener {
            val i: Intent = Intent(adapterContext, DetailActivity::class.java)
            i.putExtra(utils.EXT_OBJ, videoList[position])
            adapterContext.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

}