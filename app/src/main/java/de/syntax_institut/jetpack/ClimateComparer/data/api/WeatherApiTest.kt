package de.syntax_institut.jetpack.ClimateComparer

import de.syntax_institut.jetpack.ClimateComparer.data.api.GeoCodeApi
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val api = WeatherApi.retrofitService

    try {
        val response = api.getWeather()
        println("API Response: ${response}")
    } catch (e: Exception) {
        println("API Fehler: ${e.message}")
    }
}