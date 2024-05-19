package com.example.lostandfound.presentation.createAdvert

import android.R.attr.data
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lostandfound.R
import com.example.lostandfound.presentation.home.MainActivity
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode


class CreateAdvertActivity : ComponentActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var autocompleteLauncher: ActivityResultLauncher<Intent>
    private var onLocationReceived: ((String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        autocompleteLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (intent != null) {
                    val place = Autocomplete.getPlaceFromIntent(intent)
                    val selLocation = place.latLng?.let { "${it.latitude}, ${it.longitude}" } ?: "failed to get location"
                    onLocationReceived?.invoke(selLocation.toString())
                }
            } else if (result.resultCode == Activity.RESULT_CANCELED) {
                // The user canceled the operation.
                Log.i("PlaceData", "User canceled autocomplete")
            }
        }

        setContent {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.background
            ) {
                val viewModel = viewModel<CreateAdvertViewModel>()
                val state = viewModel.state
                val context = LocalContext.current

                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


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
                        .fillMaxSize()
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

                    Row(
                        modifier = Modifier.height(50.dp)
                    ) {
                        TextField(
                            value = state.location,
                            onValueChange = {
                                viewModel.onEvent(AdvertFormEvent.LocationChanged(it))
                            },
                            isError = state.locationError != null,
                            placeholder = {
                                Text(text = "Location")
                            },
                            readOnly = true,
                            modifier = Modifier.width(210.dp)
                        )
                        Button(onClick = {
                            getSelectedLocation{ newLocation ->
                                viewModel.onEvent(AdvertFormEvent.LocationChanged(newLocation))
                            }
                        },
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(70.dp)
                                .padding(3.dp, 0.dp, 0.dp, 0.dp)){
                            Image(
                                painter = painterResource(id = R.drawable.search_icon),
                                contentDescription = "Search icon",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        Button(onClick = {
                            getCurrentLocation() { newLocation ->
                                viewModel.onEvent(AdvertFormEvent.LocationChanged(newLocation))
                            }
                        },
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(70.dp)
                                .padding(3.dp, 0.dp, 0.dp, 0.dp)){
                            Image(
                                painter = painterResource(id = R.drawable.location_icon),
                                contentDescription = "Location icon",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }
                    if (state.locationError != null) {
                        Text(
                            text = state.locationError,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.align(Alignment.End),
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

    private fun getCurrentLocation(onLocationReceived: (String) -> Unit) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100)
            onLocationReceived("Permissions missing")
            return
        }

        val location = fusedLocationClient.lastLocation
        location.addOnSuccessListener {
            val currLocation = if (it != null) {
                "${it.latitude}, ${it.longitude}"
            } else {
                "Failed to get current location"
            }
            onLocationReceived(currLocation)
        }
    }

    private fun getSelectedLocation(onLocationReceived: (String) -> Unit) {
        this.onLocationReceived = onLocationReceived
        val fields =
            listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)
        val intent = Autocomplete.IntentBuilder(
            AutocompleteActivityMode.OVERLAY,
            fields
        ).build(this)
        autocompleteLauncher.launch(intent)
    }
}

