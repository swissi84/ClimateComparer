package de.syntax_institut.jetpack.ClimateComparer.WeatherComponents

import de.syntax_institut.jetpack.ClimateComparer.R


enum class WmoWeatherCode(val code: Int, val description: String, val imageRes: Int) {
    CLEAR_SKY(0, "Klarer Himmel", R.drawable.clear),
    PARTLY_CLOUDY(1, "Leicht bewölkt", R.drawable.partly_cloudy_day),
    CLOUDY(3, "Bewölkt", R.drawable.cloudy),
    FOG(45, "Nebel", R.drawable.fog),
    DENSE_FOG(48, "Dichter Nebel", R.drawable.fog),
    LIGHT_DRIZZLE(51, "Leichter Nieselregen", R.drawable.drizzle),
    MODERATE_DRIZZLE(53, "Mäßiger Nieselregen", R.drawable.drizzle),
    HEAVY_DRIZZLE(57, "Starker Nieselregen", R.drawable.drizzle),
    LIGHT_RAIN(61, "Leichter Regen", R.drawable.rain),
    MODERATE_RAIN(63, "Mäßiger Regen", R.drawable.rain),
    HEAVY_RAIN(67, "Starker Regen", R.drawable.rain),
    LIGHT_SNOW(71, "Leichter Schneefall", R.drawable.snow),
    MODERATE_SNOW(73, "Mäßiger Schneefall", R.drawable.snow),
    HEAVY_SNOW(77, "Starker Schneefall", R.drawable.snow),
    THUNDERSTORM(95, "Gewitter", R.drawable.thunderstorm_hail),
    SEVERE_THUNDERSTORM(99, "Schweres Gewitter", R.drawable.thunderstorm);

    companion object {
        fun fromCode(code: Int): WmoWeatherCode? {
            return entries.find { it.code == code }
        }
    }
}
