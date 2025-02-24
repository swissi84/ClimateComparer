package de.syntax_institut.jetpack.ClimateComparer.data

data class GeoCodeResponse(
    val results: List<Results>
)

data class Results(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val elevation: Double,
)