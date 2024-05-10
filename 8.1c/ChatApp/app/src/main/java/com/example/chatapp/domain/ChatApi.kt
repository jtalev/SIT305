package com.example.chatapp.domain

import com.example.chatapp.domain.entities.ChatData
import com.example.chatapp.domain.entities.ChatResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatApi {

    @POST("/chat")
    suspend fun getResponse(@Body chatData: ChatData): ChatResponse

}