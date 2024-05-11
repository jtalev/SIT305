package com.example.chatapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatapp.ui.theme.ChatAppTheme

class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(37.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatItem("Llama", "Hi, what can I help you with?wfwfsdddddddddddddddddddddddddddddsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssefwefwefefefe")
                    ChatItem(username = "Josh", message = "Hello")
                }
            }
        }
    }
}

@Composable
fun ChatItem(username: String, message: String) {
    Column(
        horizontalAlignment = if (username.equals("Llama")) {
            Alignment.Start
        } else {
            Alignment.End
        }
    ) {
        Surface(
            modifier = Modifier
                .size(35.dp)
                .background(Color.White),
            shape = CircleShape,
            color = Color.LightGray
        ) {
            Text(
                text = username.first().toString(),
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(12.dp, 6.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = message,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(4.dp)
                .width(250.dp)
        )
    }
}

@Preview
@Composable
fun PreviewChatItem() {
    ChatItem("Llama", "What can i help you with today?")
}