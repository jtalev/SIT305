package com.example.lostandfound.data

import com.example.lostandfound.presentation.showAdverts.ShowAdvertsActivity
import dagger.Component

@Component(modules = [RepositoryModule::class])
interface AppComponent {
    fun inject(activity: ShowAdvertsActivity)
}