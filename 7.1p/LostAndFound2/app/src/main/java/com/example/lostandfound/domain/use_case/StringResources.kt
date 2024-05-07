package com.example.lostandfound.domain.use_case

/**
 * Project string resources
 */
data class StringResources(
    val emptyString: String = "",
    val fieldNotBlank: String = "Field can't be blank",
    val phoneNotValid: String = "Not a valid phone number",
    val dateNotValid: String = "Date must be in format dd-MM-yyyy",
    val dateCantBeFuture: String = "Cannot be a future date"
)
