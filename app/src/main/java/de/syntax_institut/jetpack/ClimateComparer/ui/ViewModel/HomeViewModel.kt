package de.syntax_institut.jetpack.ClimateComparer.ui.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import de.syntax_institut.jetpack.ClimateComparer.data.local.FavoriteLocation
import de.syntax_institut.jetpack.ClimateComparer.data.local.FavoriteLocationsDatabase
import de.syntax_institut.jetpack.ClimateComparer.data.local.repository.FavoriteLocationsRepositoryImpl
import de.syntax_institut.jetpack.ClimateComparer.data.local.repository.FavoriteLocationsRepositoryInterface
import kotlinx.coroutines.flow.Flow




class HomeViewModel(
    application: Application
) : AndroidViewModel(application){
    private val favoriteLocationsRepository: FavoriteLocationsRepositoryInterface

    init {
        val database = FavoriteLocationsDatabase.getDataBase(application.applicationContext)
        val dao = database.dao()
        favoriteLocationsRepository = FavoriteLocationsRepositoryImpl(dao)
    }

    suspend fun getSavedFavoriteLocations(): Flow<List<FavoriteLocation>> {
        return favoriteLocationsRepository.getFavoriteLocations()
    }
}