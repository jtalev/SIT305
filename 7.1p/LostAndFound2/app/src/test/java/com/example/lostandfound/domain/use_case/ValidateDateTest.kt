package com.example.lostandfound.domain.use_case

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.util.Date

class ValidateDateTest {
    private lateinit var validateDate: ValidateDate
    private lateinit var stringResources: StringResources

    @Before
    fun setUp() {
        stringResources = StringResources()
        validateDate = ValidateDate(stringResources)
    }

    @Test
    fun dateIsEmpty() {
        val result = validateDate.execute("")

        assertEquals(result.successful, false)
    }

    @Test
    fun dateIsBlankSpace() {
        val result = validateDate.execute(" ")

        assertEquals(result.successful, false)
    }

    // date formatter is lenient
    @Test
    fun dateHasOneDayDigit() {
        val result = validateDate.execute("7-05-2024")

        assertEquals(result.successful, true)
    }

    @Test
    fun dateHasOneMonthDigit() {
        val result = validateDate.execute("07-5-2024")

        assertEquals(result.successful, true)
    }

    @Test
    fun dateHasTwoYearDigits() {
        val result = validateDate.execute("07-05-24")

        assertEquals(result.successful, true)
    }

    @Test
    fun dateHasOneDayOneMonthDigit() {
        val result = validateDate.execute("7-5-2024")

        assertEquals(result.successful, true)
    }

    @Test
    fun dateHasOneDayOneMonthTwoYearDigits() {
        val result = validateDate.execute("7-5-24")

        assertEquals(result.successful, true)
    }

    @Test
    fun dateUsesForwardSlash() {
        val result = validateDate.execute("07/05/2024")

        assertEquals(result.successful, false)
    }

    @Test
    fun dateIsFuture() {
        val tomorrow = LocalDate.now().plusDays(1).toString()
        val result = validateDate.execute(tomorrow)

        assertEquals(result.successful, false)
    }

    @Test
    fun dateIsValid() {
        val result = validateDate.execute("07-05-2024")

        assertEquals(result.successful, true)
    }
}