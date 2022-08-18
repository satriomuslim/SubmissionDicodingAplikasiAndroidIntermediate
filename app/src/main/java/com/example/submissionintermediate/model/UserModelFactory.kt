package com.example.submissionintermediate.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submissionintermediate.data.user.UserRepository
import com.example.submissionintermediate.injection.UserInjection
import com.example.submissionintermediate.viewmodel.LoginViewModel
import com.example.submissionintermediate.viewmodel.RegisterViewModel

class UserModelFactory(private val userRepo: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(userRepo) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepo) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: UserModelFactory? = null
        fun getInstance(context: Context): UserModelFactory =
            instance ?: synchronized(this) {
                instance ?: UserModelFactory(UserInjection.provideRepository(context))
            }.also { instance = it }
    }
}