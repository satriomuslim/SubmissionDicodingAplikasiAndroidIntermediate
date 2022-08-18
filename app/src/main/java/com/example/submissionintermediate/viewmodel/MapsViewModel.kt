package com.example.submissionintermediate.viewmodel

import androidx.lifecycle.ViewModel
import com.example.submissionintermediate.data.story.StoryRepository

class MapsViewModel(private val storyRepo: StoryRepository) : ViewModel() {
    fun getStories(token: String) = storyRepo.getStoryLocation(token)
}