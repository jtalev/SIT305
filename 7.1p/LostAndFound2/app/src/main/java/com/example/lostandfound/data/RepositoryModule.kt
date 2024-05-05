package com.example.lostandfound.data

import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(): IRepository {
        return MockAdvertRepository()
    }

}