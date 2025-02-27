package de.syntax_institut.jetpack.ClimateComparer.data.local.repository

import de.syntax_institut.jetpack.ClimateComparer.data.Remote.api.GeoCodeData

import de.syntax_institut.jetpack.ClimateComparer.data.local.FavoriteLocationsDao
import de.syntax_institut.jetpack.ClimateComparer.data.local.FavoriteLocation
import kotlinx.coroutines.flow.Flow

interface FavoriteLocationsRepositoryInterface {

    suspend fun convertLocationToFavoriteLocation(location: GeoCodeData): FavoriteLocation
    suspend fun addFavoriteLocation(favoriteLocation: FavoriteLocation)
    suspend fun getFavoriteLocations(): Flow<List<FavoriteLocation>>
    suspend fun deleteFavoriteLocation(favoriteLocation: FavoriteLocation)
    suspend fun getFavoriteLocationByCoordinates(latitude: Double, longitude: Double): FavoriteLocation?

}
class FavoriteLocationsRepositoryImpl(
    val databaseSource: FavoriteLocationsDao
) : FavoriteLocationsRepositoryInterface {

    override suspend fun convertLocationToFavoriteLocation(location: GeoCodeData): FavoriteLocation {
        return FavoriteLocation(
            id = location.id,
            name = location.name,
            latitude = location.latitude,
            longitude = location.longitude,
            elevation = location.elevation,
            feature_code = location.feature_code,
            country_code = location.country_code,
            timezone = location.timezone,
            population = location.population,
            country_id = location.country_id,
            country = location.country,
            )
    }

    override suspend fun addFavoriteLocation(favoriteLocation: FavoriteLocation) {
        databaseSource.insertFavoriteLocation(favoriteLocation)
    }

    override suspend fun getFavoriteLocations(): Flow<List<FavoriteLocation>> {
        return databaseSource.getFavoriteLocations()
    }

    override suspend fun deleteFavoriteLocation(favoriteLocation: FavoriteLocation) {
        databaseSource.deleteFavoriteLocation(favoriteLocation)
    }

    override suspend fun getFavoriteLocationByCoordinates(latitude: Double, longitude: Double): FavoriteLocation? {
        return databaseSource.getFavoriteLocationByCoordinates(latitude, longitude)
    }

}