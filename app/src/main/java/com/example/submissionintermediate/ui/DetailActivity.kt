package com.example.submissionintermediate.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.submissionintermediate.data.lokal.Story
import com.example.submissionintermediate.databinding.ActivityDetailBinding
import com.example.submissionintermediate.utils.setLocalDateFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val story = intent.getParcelableExtra<Story>(STORY_EXTRA)

        binding.apply {
            tvUsername.text = story?.name
            tvCreatedAt.setLocalDateFormat(story?.createdAt.toString())
            tvDescription.text = story?.description
        }
        Glide.with(this)
            .load(story?.photoUrl)
            .into(binding.imgAvatar)
    }

    companion object {
        const val STORY_EXTRA = "story_extra"
    }
}