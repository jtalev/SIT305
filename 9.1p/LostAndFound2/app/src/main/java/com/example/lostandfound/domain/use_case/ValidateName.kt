package com.example.lostandfound.domain.use_case

class ValidateName(private val stringResources: StringResources) {

    /**
     * Executes the date validation
     *
     * @param name The name string to validate.
     * @return ValidationResult indicating whether the validation was successful or not.
     */
    fun execute(name: String): ValidationResult {
        if(name.isBlank()) {
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