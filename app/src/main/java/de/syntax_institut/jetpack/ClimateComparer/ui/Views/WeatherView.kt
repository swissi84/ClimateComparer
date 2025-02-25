package de.syntax_institut.jetpack.ClimateComparer.ui.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.syntax_institut.jetpack.ClimateComparer.CompareViewModel
import de.syntax_institut.jetpack.ClimateComparer.WeatherComponents.WmoWeatherCode
import de.syntax_institut.jetpack.ClimateComparer.data.GeoCodeData
import de.syntax_institut.jetpack.ClimateComparer.data.WeatherResponse


@Composable
fun WeatherView(
    compareViewModel: CompareViewModel,
    geoCodeData: GeoCodeData,


    modifier: Modifier = Modifier,
) {

    LaunchedEffect(geoCodeData.latitude, geoCodeData.longitude) {
         compareViewModel.loadWeatherData(
             latitude = geoCodeData.latitude,
             longitude = geoCodeData.longitude,
         )
     }

     val weather by compareViewModel.weatherDataState.collectAsState()

//     val weatherEnum = WmoWeatherCode.fromCode(weather?.hourly!!.weather_code[0]) ?: WmoWeatherCode.CLEAR_SKY

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

//            Image(
//                painter = painterResource(id = weather.imageRes),
//                contentDescription = weather.description,
//                modifier = Modifier.size(100.dp)
//            )
//
//            Text(weather.description, fontSize = 16.sp)




        }
    }

}