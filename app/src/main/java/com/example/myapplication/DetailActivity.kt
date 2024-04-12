package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.adapter.ImagesAdapter
import com.example.myapplication.adapter.VideoAdapter
import com.example.myapplication.databinding.ActivityDetailLayoutBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.models.Result
import com.example.myapplication.models.getSerializable
import com.example.myapplication.ui.theme.utils
import com.example.myapplication.viewmodels.VideoViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailLayoutBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getSerializable(utils.EXT_OBJ, Result::class.java)
        binding.tvTitle.text = data.title
        binding.tvStarName.text = getString(R.string.author_name) + data.star_name
        binding.tvCategory.text = getString(R.string.category) + data.category
        var img = data.image_thumb?.replace("[", "")?.replace("]", "")?.replace("\"", "")
            ?.replace("\'", "")?.replace("https:", "")?.split(",")


        binding.btnPlayVideo.setOnClickListener {
            val i: Intent = Intent(this, VideoActivity::class.java)
            i.putExtra(utils.EXT_OBJ, data)
            startActivity(i)
        }
        binding.viewpagerImages.apply {
            layoutManager = LinearLayoutManager(
                applicationContext, LinearLayoutManager.HORIZONTAL,
                false
            )
            // adapter=ImagesAdapter(listOf(),this@DetailActivity)
            adapter = img?.let { ImagesAdapter(it, this@DetailActivity) }
        }

    }
}