package de.syntax_institut.jetpack.ClimateComparer.ui.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

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
import de.syntax_institut.jetpack.ClimateComparer.R
import de.syntax_institut.jetpack.ClimateComparer.WeatherComponents.WmoWeatherCode
import de.syntax_institut.jetpack.ClimateComparer.data.Remote.api.GeoCodeData
import de.syntax_institut.jetpack.ClimateComparer.data.local.FavoriteLocation


@Composable
fun WeatherView(
    compareViewModel: CompareViewModel,
    geoCodeData: GeoCodeData,
//    favoriteLocation: FavoriteLocation,
//    markLocationAsFavorite: (FavoriteLocation) -> Unit,
    modifier: Modifier = Modifier,

    ) {

    LaunchedEffect(geoCodeData.latitude, geoCodeData.longitude) {
         compareViewModel.loadWeatherData(
             latitude = geoCodeData.latitude,
             longitude = geoCodeData.longitude,
         )
     }

     val weatherData by compareViewModel.weatherDataState.collectAsState()

     val weather = WmoWeatherCode.fromCode(weatherData?.hourly?.weather_code?.get(11) ?: 0) ?: WmoWeatherCode.CLEAR_SKY

    Box(
        modifier = Modifier
            .fillMaxSize()

            .background(Color.Black.copy(alpha = 0.4f)),

        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

            Text(geoCodeData.name)

            Spacer(modifier.padding(20.dp))
            Image(
                painter = painterResource(id = weather.imageRes),
                contentDescription = weather.description,
                modifier = Modifier.size(100.dp)
            )

            Text(weather.description, fontSize = 16.sp)
            Spacer(modifier.padding(20.dp))
            Text("Temperatur")
            weatherData?.hourly?.temperature_2m?.get(11)?.let { Text("${it} °C") }

            Spacer(modifier.padding(20.dp))
            Text("Luftfeuchtigkeit")
            weatherData?.hourly?.relative_humidity_2m?.get(11)?.let { Text("${it} %") }

            LazyRow(
                Modifier.fillMaxWidth(),
            ) {
                weatherData?.hourly?.time?.indices?.toList()?.let { indices ->
                    items(indices.size) { index ->
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(weatherData!!.hourly.time[index])
                            Image(
                                painter = painterResource(id = WmoWeatherCode.fromCode(weatherData!!.hourly.weather_code[index])?.imageRes ?: R.drawable.clear),
                                contentDescription = null,
                                modifier = Modifier.size(50.dp)
                            )

                            weatherData?.hourly?.temperature_2m?.get(index)?.let { Text("${it} °C") }
                            weatherData?.hourly?.relative_humidity_2m?.get(index)?.let { Text("${it} %") }
                        }
                    }
                }
            }

            IconButton(
                onClick = { /*markLocationAsFavorite(favoriteLocation)*/ }
            ) {
               Icon(
                   imageVector = Icons.Default.HeartBroken,
                   tint = Color.Red,
                   contentDescription = "Like Button",

                   )
            }
        }
    }

}