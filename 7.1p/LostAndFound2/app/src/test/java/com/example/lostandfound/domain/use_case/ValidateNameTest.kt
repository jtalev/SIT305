package com.example.lostandfound.domain.use_case

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ValidateNameTest {

    private lateinit var validateName: ValidateName
    private lateinit var stringResources: StringResources

    @Before
    fun setUp() {
        stringResources = StringResources()
        validateName = ValidateName(stringResources)
    }

    @Test
    fun nameIsEmpty() {
        val result = validateName.execute("")

        assertEquals(result.successful, false)
    }

    @Test
    fun nameIsBlankSpace() {
        val result = validateName.execute(" ")

        assertEquals(result.successful, false)
    }

    @Test
    fun nameIsValid() {
        val result = validateName.execute("Josh")

        assertEquals(result.successful, true)
    }
}