package com.example.lostandfound.domain.use_case

import android.util.Patterns

class ValidatePhone(private val stringResources: StringResources) {

    /**
     * Executes the date validation
     *
     * @param phone The phone string to validate.
     * @return ValidationResult indicating whether the validation was successful or not.
     */
    fun execute(phone: String): ValidationResult {
        if(phone.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = stringResources.fieldNotBlank
            )
        }
        if(!Patterns.PHONE.matcher(phone).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = stringResources.phoneNotValid
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}