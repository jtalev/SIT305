package com.example.lostandfound.domain.use_case

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Validates a date string.
 *
 * @property stringResources StringResources object providing error messages.
 */
class ValidateDate(private var stringResources: StringResources) {

    /**
     * Executes the date validation
     *
     * @param date The date string to validate.
     * @return ValidationResult indicating whether the validation was successful or not.
     */
    fun execute(date: String): ValidationResult {
        if(date.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = stringResources.fieldNotBlank
            )
        }
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        dateFormat.isLenient = false

        try {
            val parsedDate = dateFormat.parse(date)
            val calendar = Calendar.getInstance()
            val currentDate = calendar.time

            if(parsedDate != null && parsedDate.after(currentDate)) {
                return ValidationResult(
                    successful = false,
                    errorMessage = stringResources.dateCantBeFuture
                )
            }
            if(parsedDate != null) {
                return ValidationResult(successful = true)
            } else {
                return ValidationResult(
                    successful = false,
                    errorMessage = stringResources.dateNotValid
                )
            }
        } catch (e: Exception) {
            return ValidationResult(
                successful = false,
                errorMessage = stringResources.dateNotValid
            )
        }
    }
}