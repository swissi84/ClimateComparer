package de.syntax_institut.jetpack.ClimateComparer

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val api = WeatherApi.retrofitService

    try {
        val response = api.getWeather(
            latitude = 52.52,
            longitude = 13.41,
            language = "en",
            format = "json"
        )
        println("API Response: ${response}")
    } catch (e: Exception) {
        println("API Fehler: ${e.message}")
    }
}
