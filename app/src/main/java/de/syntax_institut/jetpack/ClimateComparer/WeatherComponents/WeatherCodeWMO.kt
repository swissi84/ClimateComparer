package de.syntax_institut.jetpack.ClimateComparer.WeatherComponents

import de.syntax_institut.jetpack.ClimateComparer.R

enum class WmoWeatherCode(val code: Int, val description: String, val imageRes: Int) {
    CLEAR_SKY(0, "Klarer Himmel", R.drawable.baseline_error_outline_24);
   /* PARTLY_CLOUDY(1, "Leicht bewölkt", R.drawable.partly_cloudy),
    CLOUDY(3, "Bewölkt", R.drawable.cloudy),
    FOG(45, "Nebel", R.drawable.fog),
    DENSE_FOG(48, "Dichter Nebel", R.drawable.dense_fog),
    LIGHT_DRIZZLE(51, "Leichter Nieselregen", R.drawable.light_drizzle),
    MODERATE_DRIZZLE(53, "Mäßiger Nieselregen", R.drawable.moderate_drizzle),
    HEAVY_DRIZZLE(57, "Starker Nieselregen", R.drawable.heavy_drizzle),
    LIGHT_RAIN(61, "Leichter Regen", R.drawable.light_rain),
    MODERATE_RAIN(63, "Mäßiger Regen", R.drawable.moderate_rain),
    HEAVY_RAIN(67, "Starker Regen", R.drawable.heavy_rain),
    LIGHT_SNOW(71, "Leichter Schneefall", R.drawable.light_snow),
    MODERATE_SNOW(73, "Mäßiger Schneefall", R.drawable.moderate_snow),
    HEAVY_SNOW(77, "Starker Schneefall", R.drawable.heavy_snow),
    THUNDERSTORM(95, "Gewitter", R.drawable.thunderstorm),
    SEVERE_THUNDERSTORM(99, "Schweres Gewitter", R.drawable.severe_thunderstorm);*/

    companion object {
        fun fromCode(code: Int): WmoWeatherCode? {
            return values().find { it.code == code }
        }
    }
}
