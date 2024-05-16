package com.example.chatapp.domain.use_cases

import com.example.chatapp.data.ChatRepo
import com.example.chatapp.domain.use_cases.entities.Message
import java.lang.Exception

class SendMessageUseCase(
    private val repo: ChatRepo
) {

    suspend fun sendMessage(username: String, prompt: String) {
        val message = Message(
            messageOwner = username,
            message = prompt
        )

       repo.addMessage(message)
    }

    suspend fun receiveReply(username: String, prompt: String) {
        val message = Message(
            messageOwner = username,
            message = prompt
        )

        try {
            val reply = repo.getApiResponse(message)
            repo.addMessage(Message(
                "Llama",
                reply.message
            ))
        } catch (e: Exception) {
            repo.addMessage(Message(
                "Llama",
                "Something went wrong... please try again."
            ))
        }
    }

}