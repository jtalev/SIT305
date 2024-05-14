package com.example.chatapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.use_cases.ObserveMessagesUseCase
import com.example.chatapp.domain.use_cases.SendMessageUseCase
import com.example.chatapp.domain.use_cases.entities.Chat
import kotlinx.coroutines.launch

class ChatActivityViewModel(
    private val sendMessageUseCase: SendMessageUseCase,
    private val observeMessagesUseCase: ObserveMessagesUseCase
): ViewModel() {

    private val _chat = MutableLiveData<Chat>()
    val chat: LiveData<Chat> = _chat

    init {
        observeMessagesList()
    }

    private fun observeMessagesList() {
        viewModelScope.launch {
            observeMessagesUseCase.invoke().collect {chat ->
                _chat.postValue(chat)
            }
        }
    }

    fun sendMessage(username: String, prompt: String) {
        viewModelScope.launch {
            sendMessageUseCase(username, prompt)
        }
    }

}