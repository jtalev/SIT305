package com.example.lostandfound.presentation.home

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.lostandfound.presentation.showAdverts.ShowAdvertsActivity
import com.example.lostandfound.presentation.createAdvert.CreateAdvertActivity
import com.example.lostandfound.presentation.map.MapActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.background
            ) {
                val context = LocalContext.current

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            val intent = Intent(context, CreateAdvertActivity::class.java)
                            startActivity(intent)
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(250.dp)
                    ) {
                        Text(text = "CREATE NEW ADVERT")
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = {
                            val intent = Intent(context, ShowAdvertsActivity::class.java)
                            startActivity(intent)
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(250.dp)
                    ) {
                        Text(text = "SHOW ALL ITEMS")
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = {
                            val intent = Intent(context, MapActivity::class.java)
                            startActivity(intent)
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(250.dp)
                    ) {
                        Text(text = "SHOW ON MAP")
                    }
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}