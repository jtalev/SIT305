package com.example.lostandfound.data

interface IRepository {
    fun getAllAdverts(): List<Advert>
    fun addAdvert(advert: Advert)
    fun removeAdvert(advert: Advert): Boolean
    fun updateAdvert(advert: Advert)
    fun findAdvertById(id: Int): Advert
}