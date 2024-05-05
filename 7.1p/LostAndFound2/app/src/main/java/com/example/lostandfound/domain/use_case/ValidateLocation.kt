package com.example.lostandfound.domain.use_case

/**
 * Validates a location string.
 *
 * @property stringResources StringResources object providing error messages.
 */
class ValidateLocation(private val stringResources: StringResources) {

    /**
     * Executes the location validation.
     *
     * @param location The location string to validate.
     * @return ValidationResult indicating whether the validation was successful or not.
     */
    fun execute(location: String): ValidationResult {
        if(location.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = stringResources.fieldNotBlank
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}