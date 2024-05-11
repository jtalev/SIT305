package com.example.chatapp.data.models

import com.google.gson.annotations.SerializedName

data class ChatData(
    @SerializedName("userMessage")
    val userMessage: String,

    @SerializedName("chatHistory")
    val chatHistory: List<ChatMessage>
)
