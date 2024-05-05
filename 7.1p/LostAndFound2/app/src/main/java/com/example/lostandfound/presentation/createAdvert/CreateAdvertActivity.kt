package com.example.lostandfound.presentation.createAdvert

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lostandfound.presentation.home.MainActivity

class CreateAdvertActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.background
            ) {
                val viewModel = viewModel<CreateAdvertViewModel>()
                val state = viewModel.state
                val context = LocalContext.current
                LaunchedEffect(key1 = context) {
                    viewModel.validationEvents.collect {event ->
                        when (event) {
                            is CreateAdvertViewModel.ValidationEvent.Success -> {
                                Toast.makeText(
                                    context,
                                    "Advert submitted successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                                val intent = Intent(context, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(40.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Advert Type:",
                            modifier = Modifier.padding(end = 20.dp)
                        )
                        RadioButton(
                            selected = state.type == "Lost",
                            onClick = { viewModel.onEvent(AdvertFormEvent.TypeChanged("Lost")) },
                        )
                        Text(
                            text = "Lost",
                            modifier = Modifier.padding(end = 20.dp)
                        )
                        RadioButton(
                            selected = state.type == "Found",
                            onClick = { viewModel.onEvent(AdvertFormEvent.TypeChanged("Found")) }
                        )
                        Text(text = "Found")
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = state.name,
                        onValueChange = {
                            viewModel.onEvent(AdvertFormEvent.NameChanged(it))
                        },
                        isError = state.nameError != null,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Name")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        )
                    )
                    if (state.nameError != null) {
                        Text(
                            text = state.nameError,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    } else {
                        Text(
                            text = "",
                            color = Color.Transparent,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = state.phone,
                        onValueChange = {
                            viewModel.onEvent(AdvertFormEvent.PhoneChanged(it))
                        },
                        isError = state.phoneError != null,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Phone")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone
                        )
                    )
                    if (state.phoneError != null) {
                        Text(
                            text = state.phoneError,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    } else {
                        Text(
                            text = "",
                            color = Color.Transparent,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = state.description,
                        onValueChange = {
                            viewModel.onEvent(AdvertFormEvent.DescriptionChanged(it))
                        },
                        isError = state.descriptionError != null,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Description")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        )
                    )
                    if (state.descriptionError != null) {
                        Text(
                            text = state.descriptionError,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    } else {
                        Text(
                            text = "",
                            color = Color.Transparent,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = state.date,
                        onValueChange = {
                            viewModel.onEvent(AdvertFormEvent.DateChanged(it))
                        },
                        isError = state.dateError != null,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Date")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        )
                    )
                    if (state.dateError != null) {
                        Text(
                            text = state.dateError,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    } else {
                        Text(
                            text = "",
                            color = Color.Transparent,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = state.location,
                        onValueChange = {
                            viewModel.onEvent(AdvertFormEvent.LocationChanged(it))
                        },
                        isError = state.locationError != null,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Location")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        )
                    )
                    if (state.locationError != null) {
                        Text(
                            text = state.locationError,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.End)
                        )
                    } else {
                        Text(
                            text = "",
                            color = Color.Transparent,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                    Spacer(modifier = Modifier.height(40.dp))

                    Button(
                        onClick = {
                            viewModel.onEvent(AdvertFormEvent.Submit)
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(150.dp)
                    ) {
                        Text(text = "SUBMIT")
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = {
                            finish()
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(150.dp)
                    ) {
                        Text(text = "CLOSE")
                    }
                }
            }
        }
    }
}
