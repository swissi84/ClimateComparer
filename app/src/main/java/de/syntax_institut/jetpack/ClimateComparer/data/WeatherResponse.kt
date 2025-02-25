package de.syntax_institut.jetpack.ClimateComparer.data


data class WeatherResponse(

    var latitude: Double,
    var longitude: Double,
    var generationtime_ms: Double,
    var utc_offset_seconds: Int,
    var timezone: String,
    var timezone_abbreviation: String,
    var elevation: Double,
)