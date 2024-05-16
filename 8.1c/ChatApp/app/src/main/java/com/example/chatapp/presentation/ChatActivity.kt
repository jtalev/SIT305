package com.example.chatapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chatapp.ui.theme.ChatAppTheme
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