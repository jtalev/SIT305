package com.example.lostandfound.presentation.createAdvert

/**
 * Data class containing the state of a new advert
 *
 * @property type The type of advert, either "Lost" or "Found"
 * @property name The name of the item
 * @property nameError The name error message supplied by ValidationResult
 * @property phone The advert creators phone number
 * @property phoneError The phone error message supplied by ValidationResult
 * @property description The description of the item
 * @property descriptionError The description error message supplied by ValidationResult
 * @property date The date the item was lost or found
 * @property dateError The date error message supplied by ValidationResult
 * @property location The location or item
 * @property locationError The location error message supplied by ValidationResult
 */
data class AdvertFormState(
    val type: String = "Lost",
    val name: String = "",
    val nameError: String? = null,
    val phone: String = "",
    val phoneError: String? = null,
    val description: String = "",
    val descriptionError: String? = null,
    val date: String = "",
    val dateError: String? = null,
    val location: String = "",
    val locationError: String? = null
)
