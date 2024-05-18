package com.example.lostandfound

import android.app.Application
import com.google.android.libraries.places.api.Places


class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Places.initialize(applicationContext, "AIzaSyAs-06L2RG7mLbwKJlbf4xsrCewfkfotRs")
    }

}