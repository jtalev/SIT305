package com.example.lostandfound.presentation.showAdverts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lostandfound.data.Advert
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lostandfound.data.ProvideRepository


class ShowAdvertsActivity: ComponentActivity() {

    private var repo = ProvideRepository.getRepository()

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
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "LOST & FOUND ITEMS",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(30.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        NavList()
                    }

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
    
    @Composable
    fun NavList() {
        val navController = rememberNavController()
        
        NavHost(
            navController,
            startDestination = "advert_list"
        ) {
            composable("advert_list") {
                AdvertListView(navController = navController)
            }
            composable(
                "advert_detail/{advertId}"
            ) {navBackStackEntry ->
                val advertId = navBackStackEntry.arguments?.getString("advertId")
                AdvertDetailView(advertId = advertId, navController)
            }
        }
    }

    @Composable
    fun AdvertItem(advert: Advert, navController: NavHostController) {
        Column(
            modifier = Modifier
                .clickable {
                    navController.navigate("advert_detail/${advert.id}")
                }
        ) {
            Text(
                text = advert.type,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Name: ${advert.name}",
                fontWeight = FontWeight.Bold
            )
            Text(text = "Date: ${advert.description}")
            Text(text = "Date: ${advert.date}")
        }
    }
    
    @Composable
    fun AdvertList(adverts: List<Advert>, navController: NavHostController) {
        LazyColumn(

        ) {
            items(adverts) { advert ->
                AdvertItem(advert = advert, navController)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

    @Composable
    fun AdvertListView(navController: NavHostController) {
        val adverts = repo.getAllAdverts()
        AdvertList(adverts, navController)
    }

    @Composable
    fun AdvertDetailView(advertId: String?, navController: NavHostController) {
        val advert = remember { advertId?.let { repo.findAdvertById(it.toInt()) } }

        if (advert != null) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Name: ${advert.name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Type: ${advert.type}",
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Description: ${advert.description}",
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Date: ${advert.date}",
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Phone: ${advert.phone}",
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Location: ${advert.location}",
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        navController.navigate("advert_list")
                    },
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(text = "CLOSE")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        repo.removeAdvert(advert)
                        navController.navigate("advert_list")
                    },
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(text = "REMOVE")
                }
            }
        }
    }
}