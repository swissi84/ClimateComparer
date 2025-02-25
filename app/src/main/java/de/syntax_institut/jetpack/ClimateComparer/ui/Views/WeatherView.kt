package de.syntax_institut.jetpack.ClimateComparer.ui.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.syntax_institut.jetpack.ClimateComparer.CompareViewModel
import de.syntax_institut.jetpack.ClimateComparer.data.GeoCodeData
import de.syntax_institut.jetpack.ClimateComparer.data.WeatherResponse


@Composable
fun WeatherView(
    compareViewModel: CompareViewModel,
    geoCodeData: GeoCodeData,
    weatherResponse: WeatherResponse,

    modifier: Modifier = Modifier,
) {

     LaunchedEffect(geoCodeData.latitude, geoCodeData.longitude) {
         compareViewModel.loadWeatherData(
             latitude = geoCodeData.latitude,
             longitude = geoCodeData.longitude,
         )
     }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

//            Text(results.name)
//            Text(results.country)
//            Text(results.timezone)
//            Text(results.country_code)
//            Text(results.feature_code)
//            Text(results.id.toString())
            Text(geoCodeData.name)
            Text(weatherResponse.timezone)
            Text(weatherResponse.elevation.toString())
            Text(weatherResponse.longitude.toString())
            Text(weatherResponse.latitude.toString())


        }
    }

}