package com.ktor.testapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ktor.testapp.data.remote.PostsService
import com.ktor.testapp.data.remote.PostsServiceImpl
import com.ktor.testapp.data.remote.dto.PostResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val postsServiceImpl: PostsServiceImpl
) : ViewModel() {

    private val _postsMutableStateFlow = MutableStateFlow<List<PostResponse>>(emptyList())
    val postsMutableStateFlow = _postsMutableStateFlow

    fun getPosts() {
        viewModelScope.launch {
            _postsMutableStateFlow.value = postsServiceImpl.getPosts()
        }
    }

}