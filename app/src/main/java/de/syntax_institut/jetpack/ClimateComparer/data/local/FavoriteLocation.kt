package de.syntax_institut.jetpack.ClimateComparer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_locations")
data class FavoriteLocation(
    @PrimaryKey()
    val id: Int,

    val name: String,
    val latitude: Double,
    val longitude: Double,
    val elevation: Double = 0.0,
    val feature_code: String = "",
    val country_code: String = "",
    val timezone: String = "",
    val population: Int = 0,
    val country_id: Int = 0,
    val country: String = "",

)