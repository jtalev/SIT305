package com.example.chatapp.data

object RepositoryProvider {

    var instance: ChatRepo? = null

    fun getRepository(): ChatRepo {
        synchronized(this) {
            if (instance == null) {
                instance = ChatRepo()
            }
            return instance!!
        }
    }

}