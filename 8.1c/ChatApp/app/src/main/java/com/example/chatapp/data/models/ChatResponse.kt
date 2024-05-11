package com.example.chatapp.data.models

import com.google.gson.annotations.SerializedName

data class ChatResponse(

    @SerializedName("message")
    val message: String

)
