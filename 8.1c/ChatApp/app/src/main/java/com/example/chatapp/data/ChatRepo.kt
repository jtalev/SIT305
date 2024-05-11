package com.example.chatapp.data

import com.example.chatapp.domain.use_cases.entities.Chat
import com.example.chatapp.domain.use_cases.entities.Message

class ChatRepo: IRepository {

    private val chat: Chat = Chat(
        mutableListOf(
            Message(
                "Llama",
                "Hi, What can I help you with?"
            )
        )
    )

    override fun addMessage(message: Message) {
        chat.messages.add(message)
    }

    override fun getChatHistory(): Chat {
        return chat
    }

}