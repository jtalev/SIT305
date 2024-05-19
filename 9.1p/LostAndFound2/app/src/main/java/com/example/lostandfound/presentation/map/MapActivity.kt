package com.example.lostandfound.presentation.map

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.example.lostandfound.data.Advert
import com.example.lostandfound.data.ProvideRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.maps.android.ktx.utils.toLatLngList

class MapActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val context = LocalContext.current
                val repo = ProvideRepository.getRepository()
                val adverts = repo.getAllAdverts()
                ItemMap(adverts)
            }
        }
    }

    @Composable
    fun ItemMap(adverts: List<Advert>) {
        var isMapLoaded by remember { mutableStateOf(false) }
        val deviceLocation: LatLng = LatLng(-36.9848, 143.3906)

        val cameraPositionState = rememberCameraPositionState{
            position = CameraPosition.fromLatLngZoom(deviceLocation, 5f)
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ){
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapLoaded = {isMapLoaded = true}
            ) {
                adverts.forEach{advert ->
                    val locationString = advert.location
                    val latlng = locationString.split(", ")
                    if (latlng.size == 2) {
                        val latitude = latlng[0].toDoubleOrNull()
                        val longitude = latlng[1].toDoubleOrNull()

                        if (latitude != null && longitude != null) {
                            Marker(
                                state = rememberMarkerState(
                                    position = LatLng(latitude, longitude)
                                )
                            )
                        }
                    }
                }
            }
            Button(onClick = {
                finish()
            },
                modifier = Modifier.padding(5.dp, 5.dp, 0.dp, 0.dp)) {
                Text(text = "CLOSE")
            }
        }
    }
}

