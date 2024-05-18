package com.example.lostandfound.data

import java.lang.IllegalArgumentException

class MockAdvertRepository: IRepository{

    private val adverts: MutableList<Advert> = mutableListOf(
        Advert(
            0,
            "Lost",
            "Lost keys",
            "0408839487",
            "Lost set of keys for Volkswagen Transporter",
            "24-04-2024",
            "Park"
        ),
        Advert(
            1,
            "Found",
            "Found wallet",
            "0408839487",
            "Found black leather wallet",
            "24-04-2024",
            "Cafe"
        )
    )

    fun getNextId(): Int {
        return adverts.maxOfOrNull { it.id }?.plus(1) ?: 0
    }

    override fun getAllAdverts(): List<Advert> {
        return adverts.toList()
    }

    override fun addAdvert(advert: Advert) {
        adverts.add(advert)
    }

    override fun removeAdvert(advert: Advert): Boolean {
        val result = adverts.remove(advert)
        return result
    }

    override fun updateAdvert(advert: Advert) {
        val index = adverts.indexOfFirst { it == advert }
        if (index != -1) {
            adverts[index] = advert
        }
    }

    override fun findAdvertById(id: Int): Advert {
        val advert = adverts.find { it.id == id }
        return advert ?: throw IllegalArgumentException("No advert found for id: $id")
    }
}