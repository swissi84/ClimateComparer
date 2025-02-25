package de.syntax_institut.jetpack.ClimateComparer.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.syntax_institut.jetpack.ClimateComparer.data.model.FavoriteLocation

@Database(
    entities = [FavoriteLocation::class],
    version = 1,
    exportSchema = false
    )
abstract class FavoriteLocationsDatabase: RoomDatabase() {
    abstract fun dao(): FavoriteLocationsDao

    companion object {
        @Volatile
        private var instance: FavoriteLocationsDatabase? = null

        fun getDatabase(context: Context): FavoriteLocationsDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context, FavoriteLocationsDatabase::class.java, "favorite_locations_db"
                ).build().also { instance = it }
            }
        }
    }
}