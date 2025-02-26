package de.syntax_institut.jetpack.ClimateComparer

import de.syntax_institut.jetpack.ClimateComparer.data.Remote.api.GeoCodeApi
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val api = GeoCodeApi.retrofitService

    try {
        val response = api.searchLocation(
            location = "Berlin",
            count = 1,
            language = "en",
            format = "json",
        )
        println("API Response: ${response}")
    } catch (e: Exception) {
        println("API Fehler: ${e.message}")
    }
}
