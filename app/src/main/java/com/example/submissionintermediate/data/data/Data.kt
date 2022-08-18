package com.example.submissionintermediate.data.data

sealed class Data<out R> private constructor() {
    data class Success<out T>(val data: T) : Data<T>()
    data class Error(val error: String) : Data<Nothing>()
    object Loading : Data<Nothing>()
}