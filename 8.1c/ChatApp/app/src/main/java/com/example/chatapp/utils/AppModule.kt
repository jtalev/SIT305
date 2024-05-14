package com.example.chatapp.utils

import com.example.chatapp.data.ChatApi
import com.example.chatapp.data.ChatRepo
import com.example.chatapp.data.services.RetrofitHelper
import com.example.chatapp.domain.use_cases.ObserveMessagesUseCase
import com.example.chatapp.domain.use_cases.SendMessageUseCase
import com.example.chatapp.presentation.ChatActivityViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        val baseUrl = "http://10.0.2.2:5000/"

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().readTimeout(10, java.util.concurrent.TimeUnit.MINUTES).build())
            .build()
    }
}

val appModule = listOf(retrofitModule) + module {
    single { get<Retrofit>().create(ChatApi::class.java) }
    single {ChatRepo(get())}
    factory { SendMessageUseCase(get()) }
    factory { ObserveMessagesUseCase(get()) }
    viewModel { ChatActivityViewModel(get(), get())}
}