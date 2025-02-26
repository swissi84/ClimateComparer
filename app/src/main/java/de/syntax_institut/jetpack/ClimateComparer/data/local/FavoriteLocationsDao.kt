package de.syntax_institut.jetpack.ClimateComparer.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteLocationsDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertFavoriteLocation(favoriteLocation: FavoriteLocation)

    @Query("SELECT * FROM favorite_locations")
    fun getFavoriteLocations(): Flow<List<FavoriteLocation>>

    @Delete
    suspend fun deleteFavoriteLocation(favoriteLocation: FavoriteLocation)
}