package com.example.submissionintermediate.viewmodel

import androidx.lifecycle.ViewModel
import com.example.submissionintermediate.data.user.UserRepository

class RegisterViewModel (private val repo: UserRepository) : ViewModel() {

    fun register(name: String, email: String, password: String) = repo.register(name, email, password)
}