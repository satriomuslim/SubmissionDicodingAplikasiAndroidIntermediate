package com.example.submissionintermediate.data.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.*
import com.example.submissionintermediate.api.ApiService
import com.example.submissionintermediate.data.add.AddStoryResponse
import com.example.submissionintermediate.data.data.Data
import com.example.submissionintermediate.data.lokal.Story
import com.example.submissionintermediate.database.StoryDatabase
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StoryRepository(private val apiService: ApiService, private val storyDatabase: StoryDatabase){

    fun getStory(token: String): LiveData<PagingData<Story>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = StoryRemoteMediator(storyDatabase, apiService, token),
            pagingSourceFactory = {
                storyDatabase.storyDao().getAllStories()
            }
        ).liveData
    }

    fun getStoryLocation(token: String) : LiveData<Data<StoryResponse>> = liveData{
        emit(Data.Loading)
        try {
            val client = apiService.getStories("Bearer $token", location = 1)
            emit(Data.Success(client))
        }catch (e : java.lang.Exception){
            emit(Data.Error(e.message.toString()))
        }
    }

    fun uploadStory(token: String, imageMultipart: MultipartBody.Part, desc: RequestBody, lat: RequestBody?, lon: RequestBody?):
            LiveData<Data<AddStoryResponse>> = liveData{
        emit(Data.Loading)
        try {
            val client = apiService.uploadStory("Bearer $token",imageMultipart, desc)
            emit(Data.Success(client))
        }catch (e : Exception){
            emit(Data.Error(e.message.toString()))
        }
    }

}