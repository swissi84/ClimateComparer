package de.syntax_institut.jetpack.ClimateComparer

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.jetpack.ClimateComparer.data.Remote.api.GeoCodeData
import de.syntax_institut.jetpack.ClimateComparer.data.Remote.api.WeatherResponse
import de.syntax_institut.jetpack.ClimateComparer.data.Remote.api.GeoCodeApi
import de.syntax_institut.jetpack.ClimateComparer.data.local.FavoriteLocation
import de.syntax_institut.jetpack.ClimateComparer.data.local.FavoriteLocationsDatabase
import de.syntax_institut.jetpack.ClimateComparer.data.local.repository.FavoriteLocationsRepositoryImpl
import de.syntax_institut.jetpack.ClimateComparer.data.local.repository.FavoriteLocationsRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CompareViewModel(
application: Application
) : AndroidViewModel(application){
    private val favoriteLocationsRepository: FavoriteLocationsRepositoryInterface

    init {
    val database = FavoriteLocationsDatabase.getDataBase(application.applicationContext)
    val dao = database.dao()
    favoriteLocationsRepository = FavoriteLocationsRepositoryImpl(dao)
}

    private val apiGeoCode = GeoCodeApi.retrofitService
    private val apiWeather = WeatherApi.retrofitService

    private val _geoCodeDataState = MutableStateFlow<List<GeoCodeData>>(emptyList())
    val geoCodeDataState = _geoCodeDataState.asStateFlow()

    private val _weatherDataState = MutableStateFlow<WeatherResponse?>(null)
    val weatherDataState = _weatherDataState.asStateFlow()

    suspend fun isFavoriteLocationExists(latitude: Double, longitude: Double): Boolean {
        return favoriteLocationsRepository.getFavoriteLocationByCoordinates(latitude, longitude) != null
    }


    fun loadGeoCode(geoSearch: String) {
        viewModelScope.launch {
            try {
                val response = apiGeoCode.searchLocation(
                    location = geoSearch,
                    count = 100,
                    language = "en",
                    format = "json"
                )
                _geoCodeDataState.value = response.results

            } catch (e: Exception) {
                Log.e("loadGeoCode", "Error: $e")
                _geoCodeDataState.value = emptyList()
            }
        }
    }

    fun loadWeatherData(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                val response = apiWeather.getWeather(
                    latitude = latitude,
                    longitude= longitude,

                )
                _weatherDataState.value = response

            } catch (e: Exception) {
                Log.e("loadGeoCode", "Error: $e")
                _weatherDataState.value = null
            }
        }
    }

    fun markAsFavoriteLocation(location: GeoCodeData) {
        viewModelScope.launch {
            try {
                val favoriteLocation = favoriteLocationsRepository.convertLocationToFavoriteLocation(location)
            favoriteLocationsRepository.addFavoriteLocation(favoriteLocation)
            } catch (e: Exception) {
                Log.e(
                    "ERROR",
                    "Fehler beim Einfügen des zu speichernden Standortes in die DB ${e.localizedMessage}"
                )
            }
        }
    }

    fun markAsFavoriteLocation2(location: GeoCodeData) {
        viewModelScope.launch {
            try {
                val existingFavorite = favoriteLocationsRepository.getFavoriteLocationByCoordinates(
                    latitude = location.latitude,
                    longitude = location.longitude,
                )
                if (existingFavorite == null) {
                    val favoriteLocation = FavoriteLocation(
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
                    favoriteLocationsRepository.addFavoriteLocation(favoriteLocation)
                } else {

                    favoriteLocationsRepository.deleteFavoriteLocation(existingFavorite)
                }
            } catch (e: Exception) {
                Log.e(
                    "ERROR",
                    "Fehler beim Speichern oder Entfernen des Favoriten ${e.localizedMessage}"
                )
            }
        }
    }

    fun toggleFavoriteLocation(location: GeoCodeData, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {

                val favoriteLocation = favoriteLocationsRepository.convertLocationToFavoriteLocation(location)
                favoriteLocationsRepository.addFavoriteLocation(favoriteLocation)
            } else {

                val favoriteLocation = favoriteLocationsRepository.convertLocationToFavoriteLocation(location)
                favoriteLocationsRepository.deleteFavoriteLocation(favoriteLocation)
            }
        }
    }
}