package com.example.lostandfound.domain.use_case

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ValidateLocationTest {
    private lateinit var validateLocation: ValidateLocation
    private lateinit var stringResources: StringResources

    @Before
    fun setUp() {
        stringResources = StringResources()
        validateLocation = ValidateLocation(stringResources)
    }

    @Test
    fun locationIsEmpty() {
        val result = validateLocation.execute("")

        assertEquals(result.successful, false)
    }

    @Test
    fun locationIsBlankSpace() {
        val result = validateLocation.execute(" ")

        assertEquals(result.successful, false)
    }

    @Test
    fun locationIsValid() {
        val result = validateLocation.execute("Valid description")

        assertEquals(result.successful, true)
    }
}