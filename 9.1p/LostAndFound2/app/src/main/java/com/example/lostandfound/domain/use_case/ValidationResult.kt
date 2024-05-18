package com.example.lostandfound.domain.use_case

/**
 * The result returned by a validation class
 */
data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
