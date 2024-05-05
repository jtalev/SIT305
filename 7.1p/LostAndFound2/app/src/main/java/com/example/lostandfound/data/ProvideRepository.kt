package com.example.lostandfound.data

object ProvideRepository {
    var instance: MockAdvertRepository? = null

    fun getRepository(): MockAdvertRepository {
        synchronized(this) {
            if (instance == null) {
                instance = MockAdvertRepository()
            }
            return instance!!
        }
    }

}