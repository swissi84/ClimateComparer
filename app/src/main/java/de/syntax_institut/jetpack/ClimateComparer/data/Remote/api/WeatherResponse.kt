package de.syntax_institut.jetpack.ClimateComparer.data.Remote.api

import kotlinx.serialization.Serializable

data class WeatherResponse(
    var latitude: Double,
    var longitude: Double,
    var generationtime_ms: Double,
    var utc_offset_seconds: Int,
    var timezone: String,
    var timezone_abbreviation: String,
    var elevation: Double,
//    val hourly_units: HourlyUnits,
    val hourly: HourlyData,

    )

@Serializable
data class HourlyData(
    val time: List<String> = emptyList(),
    val temperature_2m: List<Double> = emptyList(),
    val relative_humidity_2m: List<Int> = emptyList(),
    val weather_code: List<Int> = emptyList()
)


//@Serializable
//data class HourlyUnits(
//    val time: String,
//    val temperature_2m: String,
//    val relative_humidity_2m: String,
//    val weather_code: String
//)