package de.syntax_institut.jetpack.ClimateComparer.data.repository

import de.syntax_institut.jetpack.ClimateComparer.data.Results
import de.syntax_institut.jetpack.ClimateComparer.data.local.FavoriteLocationsDao
import de.syntax_institut.jetpack.ClimateComparer.data.model.FavoriteLocation
import kotlinx.coroutines.flow.Flow

interface FavoriteLocationsRepositoryInterface {

    suspend fun convertLocationToFavoriteLocation(location: Results): FavoriteLocation
    suspend fun addFavoriteLocation(favoriteLocation: FavoriteLocation)
    suspend fun getFavoriteLocations(): Flow<List<FavoriteLocation>>
    suspend fun deleteFavoriteLocation(favoriteLocation: FavoriteLocation)

}
class FavoriteLocationsRepositoryImpl(
    val databaseSource: FavoriteLocationsDao
) : FavoriteLocationsRepositoryInterface {

    override suspend fun convertLocationToFavoriteLocation(location: Results): FavoriteLocation {
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
}