package com.ktor.testapp.data.remote

import com.ktor.testapp.data.remote.dto.PostRequest
import com.ktor.testapp.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsServiceImpl(
    private val client: HttpClient
) : PostsService {
    override suspend fun getPosts(): List<PostResponse> =
        withContext(Dispatchers.IO) {
            try {
                client.get { url(HttpRoutes.POSTS) }
            } catch (ex: RedirectResponseException) {
                //3xx - responses
                println("Error ${ex.response.status.description}")
                emptyList()
            } catch (ex: ClientRequestException) {
                //4xx - responses
                println("Error ${ex.response.status.description}")
                emptyList()
            } catch (ex: ServerResponseException) {
                //5xx - responses
                println("Error ${ex.response.status.description}")
                emptyList()
            } catch (ex: Exception) {
                //5xx - responses
                println("Error ${ex.message}")
                emptyList()
            }
        }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? =
        withContext(Dispatchers.IO) {
            try {
                client.post<PostResponse> {
                    url(HttpRoutes.POSTS)
                    contentType(ContentType.Application.Json)
                    body = postRequest
                }
            } catch (ex: RedirectResponseException) {
                //3xx - responses
                println("Error ${ex.response.status.description}")
                null
            } catch (ex: ClientRequestException) {
                //4xx - responses
                println("Error ${ex.response.status.description}")
                null
            } catch (ex: ServerResponseException) {
                //5xx - responses
                println("Error ${ex.response.status.description}")
                null
            } catch (ex: Exception) {
                //5xx - responses
                println("Error ${ex.message}")
                null
            }
        }
}