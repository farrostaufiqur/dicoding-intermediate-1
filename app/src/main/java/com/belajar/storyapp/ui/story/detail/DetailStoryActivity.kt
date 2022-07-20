package com.belajar.storyapp.ui.story.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.belajar.storyapp.const.Constant
import com.belajar.storyapp.databinding.ActivityDetailStoryBinding
import com.belajar.storyapp.network.response.StoriesResponse
import com.bumptech.glide.Glide

class DetailStoryActivity : AppCompatActivity() {
    private var _binding: ActivityDetailStoryBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        val story: StoriesResponse.Story? = intent.getParcelableExtra(Constant.BUNDLE_STORY)

        binding?.apply {
            Glide.with(this@DetailStoryActivity)
                .load(story?.photoUrl)
                .into(storyDetailImg)
            storyDetailTitle.text = story?.name
            storyDetailDesc.text = story?.description
        }
    }
}