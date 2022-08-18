package com.example.submissionintermediate.injection

import android.content.Context
import com.example.submissionintermediate.api.ApiConfig
import com.example.submissionintermediate.data.story.StoryRepository
import com.example.submissionintermediate.database.StoryDatabase

object StoryInjection {
    fun provideRepository(context: Context): StoryRepository {
        val apiService = ApiConfig.getApiService()
        val database = StoryDatabase.getDatabase(context)
        return StoryRepository(apiService, database)
    }
}