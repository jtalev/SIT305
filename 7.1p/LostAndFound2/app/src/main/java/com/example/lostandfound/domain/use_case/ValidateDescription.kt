package com.example.lostandfound.domain.use_case

class ValidateDescription(private val stringResources: StringResources) {

    /**
     * Executes the date validation
     *
     * @param description The description string to validate.
     * @return ValidationResult indicating whether the validation was successful or not.
     */
    fun execute(description: String): ValidationResult {
        if(description.isBlank()) {
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