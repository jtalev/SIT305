package com.example.chatapp.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.example.chatapp.domain.use_cases.entities.Chat
import kotlinx.coroutines.launch

data class UIHandler(
    val onSendMessage: (String, String) -> Unit = { s: String, s1: String -> }
)

@Composable
fun ChatScreen(
    username: String,
    chat: LiveData<Chat>,
    onSendMessage: (String, String) -> Unit
) {
    Log.d("ChatScreen", "Chat screen composing")
    var inputValue by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val chatState by chat.observeAsState()

    fun sendMessage() {
        onSendMessage(username, inputValue)
        inputValue = ""
        coroutineScope.launch {
            listState.animateScrollToItem(chatState?.messages?.size ?: 0)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier.weight(1f)) {
            chatState?.let {
                ChatList(
                    chat = it,
                    chatState = listState
                )
            }
        }
        Row {
            TextField(
                value = inputValue,
                onValueChange = {inputValue = it},
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions {
                    sendMessage()
                },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Button(
                modifier = Modifier.height(56.dp),
                onClick = { sendMessage() },
                enabled = inputValue.isNotBlank()
            ) {
                Text(text = "Send")
            }
        }
    }
}

@Composable
fun ChatList(chat: Chat, chatState: LazyListState) {
    LazyColumn(
        state = chatState
    ){
        items(chat.messages) { message ->
            Column(
                horizontalAlignment = if (message.messageOwner == "Llama") {
                    Alignment.Start
                } else {
                    Alignment.End
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    modifier = Modifier
                        .size(35.dp)
                        .background(Color.White),
                    shape = CircleShape,
                    color = if (message.messageOwner == "Llama") {
                        Color(android.graphics.Color.parseColor("#5B6B74"))
                    } else {
                        Color(android.graphics.Color.parseColor("#3C89B2"))
                    }
                ) {
                    Text(
                        text = message.messageOwner.first().toString(),
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(12.dp, 6.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = message.message,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(4.dp)
                        .width(250.dp),
                    textAlign = if (message.messageOwner == "Llama") {
                        TextAlign.Start
                    } else {
                        TextAlign.End
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}