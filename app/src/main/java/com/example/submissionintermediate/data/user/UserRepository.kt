package com.example.submissionintermediate.data.user

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.submissionintermediate.api.ApiService
import com.example.submissionintermediate.data.data.Data
import com.example.submissionintermediate.data.login.LoginResponse
import com.example.submissionintermediate.data.register.RegisterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository private constructor(
    private val dataStore: DataStore<Preferences>,
    private val apiService: ApiService
){

    fun register(name: String, email: String, password: String) : LiveData<Data<RegisterResponse>> = liveData {
        emit(Data.Loading)
        try {
            val result = apiService.register(name, email, password)
            emit(Data.Success(result))
        }catch (e : Exception){
            e.printStackTrace()
            emit(Data.Error(e.message.toString()))
        }
    }

    fun login(email: String, password: String) : LiveData<Data<LoginResponse>> = liveData {
        emit(Data.Loading)
        try {
            val result = apiService.login(email, password)
            emit(Data.Success(result))
        }catch (e : Exception){
            e.printStackTrace()
            emit(Data.Error(e.message.toString()))
        }
    }

    fun getToken() : Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN] ?: ""
        }
    }

    fun isLogin() : Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[STATE_KEY] ?: false
        }
    }

    suspend fun setToken(token: String, isLogin: Boolean) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = token
            preferences[STATE_KEY] = isLogin
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[TOKEN] = ""
            preferences[STATE_KEY] = false
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null

        private val TOKEN = stringPreferencesKey("token")
        private val STATE_KEY = booleanPreferencesKey("state")

        fun getInstance(
            dataStore: DataStore<Preferences>,
            apiService: ApiService
        ): UserRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = UserRepository(dataStore, apiService)
                INSTANCE = instance
                instance
            }
        }
    }
}