package com.example.chatapp.data

import com.example.chatapp.data.models.ChatData
import com.example.chatapp.data.models.ChatResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApi {

    @POST("/chat")
    suspend fun getResponse(@Body chatData: ChatData): ChatResponse

}