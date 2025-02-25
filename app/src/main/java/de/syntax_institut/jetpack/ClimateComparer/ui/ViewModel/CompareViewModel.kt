package de.syntax_institut.jetpack.ClimateComparer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.jetpack.ClimateComparer.data.GeoCodeData
import de.syntax_institut.jetpack.ClimateComparer.data.WeatherResponse
import de.syntax_institut.jetpack.ClimateComparer.data.api.GeoCodeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class CompareViewModel: ViewModel() {

    private val apiGeoCode = GeoCodeApi.retrofitService
    private val apiWeather = WeatherApi.retrofitService

    private val _geoCodeDataState = MutableStateFlow<List<GeoCodeData>>(emptyList())
    val geoCodeDataState = _geoCodeDataState.asStateFlow()

    private val _weatherDataState = MutableStateFlow<WeatherResponse?>(null)
    val weatherDataState = _weatherDataState.asStateFlow()

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
                    language = "en",
                    format = "json"
                )
                _weatherDataState.value = response

            } catch (e: Exception) {
                Log.e("loadGeoCode", "Error: $e")
                _weatherDataState.value = null
            }
        }
    }


}
