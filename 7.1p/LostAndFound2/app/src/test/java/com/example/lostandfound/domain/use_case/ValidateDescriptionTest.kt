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
    fun nameIsEmpty() {
        val result = validateDescription.execute("")

        assertEquals(result.successful, false)
    }

    @Test
    fun nameIsBlankSpace() {
        val result = validateDescription.execute(" ")

        assertEquals(result.successful, false)
    }

    @Test
    fun nameIsValid() {
        val result = validateDescription.execute("Valid description")

        assertEquals(result.successful, true)
    }
}