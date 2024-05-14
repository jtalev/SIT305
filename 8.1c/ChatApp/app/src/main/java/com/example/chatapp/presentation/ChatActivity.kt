package com.example.chatapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatapp.domain.use_cases.entities.Message
import com.example.chatapp.ui.theme.ChatAppTheme
import com.example.chatapp.domain.use_cases.entities.Chat
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class ChatActivity : ComponentActivity() {

    private val viewModel: ChatActivityViewModel by stateViewModel(
        state = { intent?.extras ?: Bundle() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val username = intent.getStringExtra("username")

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        ChatScreen(
                            username = username?: "",
                            chat = viewModel.chat,
                            onSendMessage = viewModel::sendMessage
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChatList(messageList: List<Message>, chatState: LazyListState) {
    LazyColumn(
        state = chatState
    ){
        items(messageList) {message ->
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
    val messageState = rememberLazyListState()
    val chatState by chat.observeAsState()

    fun sendMessage() {
        onSendMessage(username, inputValue)
        inputValue = ""
        coroutineScope.launch {
            messageState.animateScrollToItem(chatState?.messages?.size ?: 0)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier.weight(1f)) {
            chatState?.let {
                ChatList(
                    messageList = it.messages,
                    chatState = messageState
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