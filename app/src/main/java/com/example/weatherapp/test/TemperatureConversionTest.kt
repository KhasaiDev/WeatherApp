package com.example.weatherapp.test

import org.junit.Assert.assertEquals
import org.junit.Test

class TemperatureConversionTest {

    @Test
    fun celsiusToFahrenheit_isCorrect() {
        assertEquals(32.0, convertCelsiusToFahrenheit(0.0), 0.001)
        assertEquals(212.0, convertCelsiusToFahrenheit(100.0), 0.001)
        assertEquals(98.6, convertCelsiusToFahrenheit(37.0), 0.001)
    }

    @Test
    fun fahrenheitToCelsius_isCorrect() {
        assertEquals(0.0, convertFahrenheitToCelsius(32.0), 0.001)
        assertEquals(100.0, convertFahrenheitToCelsius(212.0), 0.001)
        assertEquals(37.0, convertFahrenheitToCelsius(98.6), 0.001)
    }

    private fun convertCelsiusToFahrenheit(celsius: Double): Double {
        return celsius * 9 / 5 + 32
    }

    private fun convertFahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }
}