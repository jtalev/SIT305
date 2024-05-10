package com.example.chatapp.domain.entities

import com.google.gson.annotations.SerializedName

data class ChatMessage(
    @SerializedName("User")
    val user: String,

    @SerializedName("Llama")
    val llama: String
)
