package com.ktor.testapp.di

import com.ktor.testapp.data.remote.PostsServiceImpl
import com.ktor.testapp.ui.main.MainViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val serviceApiModule = module {
    fun getHttpClient(): HttpClient =
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }

    single { getHttpClient() }
    single { PostsServiceImpl(get()) }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}