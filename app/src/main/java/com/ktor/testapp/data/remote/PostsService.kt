package com.ktor.testapp.data.remote

import com.ktor.testapp.data.remote.dto.PostRequest
import com.ktor.testapp.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PostsService {
    suspend fun getPosts() :List<PostResponse>

    suspend fun createPost(postRequest: PostRequest):PostResponse?

    companion object {
        fun create() : PostsService {
            return PostsServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}