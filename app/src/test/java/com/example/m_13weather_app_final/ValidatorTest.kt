package com.example.m_13weather_app_final


import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest{
    @Test
    fun whenInputIsValid(){
        val amount = 100
        val desc = "some description"
        val result = Validator.validateInput(amount,desc)

        assertThat(result).isEqualTo(true)

    }

    @Test
    fun whenInputIsInvalid(){
        val amount = 0
        val desc = ""
        val result = Validator.validateInput(amount,desc)

        assertThat(result).isEqualTo(false)

    }
}