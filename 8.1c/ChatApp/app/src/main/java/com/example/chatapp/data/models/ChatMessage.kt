package com.example.chatapp.data.models

import com.google.gson.annotations.SerializedName

data class ChatMessage(
    @SerializedName("User")
    val user: String,

    @SerializedName("Llama")
    val llama: String
)
