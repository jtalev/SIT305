package com.example.chatapp.domain.use_cases

import com.example.chatapp.data.ChatRepo
import com.example.chatapp.domain.use_cases.entities.Message
import java.lang.Exception

class SendMessageUseCase(
    private val repo: ChatRepo
) {

    suspend operator fun invoke(username: String, prompt: String) {
        val message = Message(
            messageOwner = username,
            message = prompt
        )

       repo.addMessage(message)

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