package com.example.myapplication

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityVideoPlayBinding
import com.example.myapplication.models.Result
import com.example.myapplication.models.getSerializable
import com.example.myapplication.ui.theme.utils

class VideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.getSerializable(utils.EXT_OBJ, Result::class.java)
        val uri: Uri = Uri.parse(data.video_url)
        binding.videoView.setVideoURI(uri)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)
        mediaController.setMediaPlayer(binding.videoView)
        binding.videoView.setMediaController(mediaController)
        binding.videoView.start()


    }
}