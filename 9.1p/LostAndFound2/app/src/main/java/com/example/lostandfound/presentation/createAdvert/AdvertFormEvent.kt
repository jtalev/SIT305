package com.example.lostandfound.presentation.createAdvert

sealed class AdvertFormEvent {
    data class TypeChanged(val type: String): AdvertFormEvent()
    data class NameChanged(val name: String): AdvertFormEvent()
    data class PhoneChanged(val phone: String): AdvertFormEvent()
    data class DescriptionChanged(val description: String): AdvertFormEvent()
    data class DateChanged(val date: String): AdvertFormEvent()
    data class LocationChanged(val location: String): AdvertFormEvent()
    data object Submit: AdvertFormEvent()
}