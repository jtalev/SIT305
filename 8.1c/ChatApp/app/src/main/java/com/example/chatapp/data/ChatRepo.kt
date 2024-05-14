package com.example.chatapp.data

import com.example.chatapp.data.models.ChatData
import com.example.chatapp.data.models.ChatMessage
import com.example.chatapp.data.models.ChatResponse
import com.example.chatapp.data.services.RetrofitHelper
import com.example.chatapp.domain.use_cases.entities.Chat
import com.example.chatapp.domain.use_cases.entities.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatRepo(
    private val chatApi: ChatApi
){

    private var chat = mutableListOf(
        Message(
            "Llama",
            "Hi, What can I help you with?"
        )
    )

    private val _chatFlow = MutableStateFlow(
        value = Chat(messages = chat)
    )
    val chatFlow = _chatFlow.asStateFlow()

    fun addMessage(message: Message): Chat {
        chat.add(message)
        return updateChatFlow(chat)
    }

    fun updateChatFlow(chat: List<Message>): Chat {
        val conversation = Chat(
            messages = chat
        )
        _chatFlow.value = conversation
        return conversation
    }

    suspend fun getApiResponse(message: Message): ChatResponse {
        val chatHistory = mutableListOf<ChatMessage>()
        var userMessage = ""
        var llamaMessage = ""

        for (prompt in chat) {
            if (prompt.messageOwner == "Llama") {
                llamaMessage = prompt.message
            } else {
                userMessage = prompt.message
            }

            if (userMessage.isNotBlank() && llamaMessage.isNotBlank()){
                val data = ChatMessage(
                    userMessage,
                    llamaMessage
                )
                chatHistory.add(data)
                userMessage = ""
                llamaMessage = ""
            }
        }

        val data = ChatData(
            message.message,
            chatHistory = chatHistory
        )

        return chatApi.getResponse(data)
    }

}