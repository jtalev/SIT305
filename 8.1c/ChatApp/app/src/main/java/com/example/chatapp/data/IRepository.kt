package com.example.chatapp.data

import com.example.chatapp.domain.use_cases.entities.Chat
import com.example.chatapp.domain.use_cases.entities.Message

interface IRepository {
    fun addMessage(message: Message)
    fun getChatHistory(): Chat
}