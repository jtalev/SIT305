package com.example.chatapp.domain.entities

import com.google.gson.annotations.SerializedName

data class ChatResponse(

    @SerializedName("message")
    val message: String

)
