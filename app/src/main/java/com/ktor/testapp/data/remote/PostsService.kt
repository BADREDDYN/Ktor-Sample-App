package com.ktor.testapp.data.remote

import com.ktor.testapp.data.remote.dto.PostRequest
import com.ktor.testapp.data.remote.dto.PostResponse

interface PostsService {
    suspend fun getPosts() :List<PostResponse>

    suspend fun createPost(postRequest: PostRequest):PostResponse?
}