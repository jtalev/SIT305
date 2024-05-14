package com.example.chatapp.domain.use_cases

import com.example.chatapp.data.ChatRepo

class ObserveMessagesUseCase(
    private val repo: ChatRepo
) {

    operator fun invoke() = repo.chatFlow

}