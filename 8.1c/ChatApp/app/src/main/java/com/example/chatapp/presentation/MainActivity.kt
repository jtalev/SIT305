package com.example.chatapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.chatapp.domain.ChatApi
import com.example.chatapp.domain.entities.ChatData
import com.example.chatapp.domain.entities.ChatMessage
import com.example.chatapp.domain.services.RetrofitHelper
import com.example.chatapp.ui.theme.ChatAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current

                    MainScreen(context)
                }
            }
        }
    }
}

@Composable
fun MainScreen(context: Context) {
    var username by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(37.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome,",
            fontSize = 40.sp
        )
        Text(
            text = "Let's Chat!",
            fontSize = 50.sp
        )
        Spacer(modifier = Modifier.height(100.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Username") }
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (username.isBlank()) {
                    Toast.makeText(context, "Enter a username", Toast.LENGTH_LONG).show()
                } else {
                    val intent = Intent(context, ChatActivity::class.java)
                    intent.putExtra("username", username)
                    startActivity(context, intent, null)
                }

            },
            modifier = Modifier
                .width(150.dp)
        ) {
            Text(text = "Go")
        }
    }
}
