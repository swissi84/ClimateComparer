package de.syntax_institut.jetpack.ClimateComparer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntax_institut.jetpack.ClimateComparer.data.GeoCodeResponse
import de.syntax_institut.jetpack.ClimateComparer.data.Results
import de.syntax_institut.jetpack.ClimateComparer.data.api.GeoCodeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


open class CompareViewModel: ViewModel() {

    private val api = GeoCodeApi.retrofitService

    private val _geoCodeDataState = MutableStateFlow<List<Results>>(emptyList())
    val geoCodeDataState = _geoCodeDataState.asStateFlow()

    fun loadGeoCode(geoSearch: String) {
        viewModelScope.launch {
            try {
                val response = api.searchLocation(
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
}
