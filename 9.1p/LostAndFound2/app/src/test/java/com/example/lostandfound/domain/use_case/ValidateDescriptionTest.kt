package com.example.lostandfound.domain.use_case

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ValidateDescriptionTest {
    private lateinit var validateDescription: ValidateDescription
    private lateinit var stringResources: StringResources

    @Before
    fun setUp() {
        stringResources = StringResources()
        validateDescription = ValidateDescription(stringResources)
    }

    @Test
    fun descriptionIsEmpty() {
        val result = validateDescription.execute("")

        assertEquals(result.successful, false)
    }

    @Test
    fun descriptionIsBlankSpace() {
        val result = validateDescription.execute(" ")

        assertEquals(result.successful, false)
    }

    @Test
    fun descriptionIsValid() {
        val result = validateDescription.execute("Valid description")

        assertEquals(result.successful, true)
    }
}