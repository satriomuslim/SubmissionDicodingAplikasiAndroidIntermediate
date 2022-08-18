package com.example.submissionintermediate.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submissionintermediate.data.story.StoryRepository
import com.example.submissionintermediate.data.user.UserRepository
import com.example.submissionintermediate.injection.StoryInjection
import com.example.submissionintermediate.injection.UserInjection
import com.example.submissionintermediate.viewmodel.AddStoryViewModel
import com.example.submissionintermediate.viewmodel.MainViewModel
import com.example.submissionintermediate.viewmodel.MapsViewModel

class AddStoryModelFactory private constructor(private val userRepo: UserRepository, private val storyRepo: StoryRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userRepo, storyRepo) as T
            }
            modelClass.isAssignableFrom(AddStoryViewModel::class.java) -> {
                AddStoryViewModel(userRepo, storyRepo) as T
            }
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
                MapsViewModel(storyRepo) as T
            }
            else -> {
                throw IllegalArgumentException("Illegal ViewModel class: " + modelClass.name)
            }
        }
    }

    companion object {
        @Volatile
        private var instance: AddStoryModelFactory? = null
        fun getInstance(context: Context): AddStoryModelFactory =
            instance ?: synchronized(this) {
                instance ?: AddStoryModelFactory(UserInjection.provideRepository(context),
                    StoryInjection.provideRepository(context))
            }.also { instance = it }
    }
}