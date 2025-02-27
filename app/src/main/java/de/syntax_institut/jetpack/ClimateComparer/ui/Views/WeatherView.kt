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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.syntax_institut.jetpack.ClimateComparer.CompareViewModel
import de.syntax_institut.jetpack.ClimateComparer.R
import de.syntax_institut.jetpack.ClimateComparer.WeatherComponents.WmoWeatherCode
import de.syntax_institut.jetpack.ClimateComparer.data.Remote.api.GeoCodeData
import java.util.Calendar


@Composable
fun WeatherView(
    compareViewModel: CompareViewModel,
    geoCodeData: GeoCodeData,
//    favoriteLocation: FavoriteLocation,
//    markLocationAsFavorite: (FavoriteLocation) -> Unit,
    modifier: Modifier = Modifier,

    ) {

    var isFavorite by remember { mutableStateOf(false) }

    LaunchedEffect(geoCodeData.latitude, geoCodeData.longitude) {
        compareViewModel.loadWeatherData(
            latitude = geoCodeData.latitude,
            longitude = geoCodeData.longitude,
        )
    }

    val weatherData by compareViewModel.weatherDataState.collectAsState()

    val calendar = Calendar.getInstance()
    val currentHour = calendar.get(Calendar.HOUR_OF_DAY)

    val weather = WmoWeatherCode.fromCode(weatherData?.hourly?.weather_code?.get(currentHour - 1) ?: 0)
        ?: WmoWeatherCode.CLEAR_SKY

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

            Text(
               text = geoCodeData.name,
                style = MaterialTheme.typography.headlineLarge
            )

            Image(
                painter = painterResource(id = weather.imageRes),
                contentDescription = weather.description,
                modifier = Modifier.size(150.dp)
            )

            Text(weather.description, fontSize = 20.sp)

            Spacer(modifier.padding(20.dp))
            Text("Temperatur")
            weatherData?.hourly?.temperature_2m?.get(11)?.let { Text("${it} °C") }

            Spacer(modifier.padding(20.dp))
            Text("Luftfeuchtigkeit")
            weatherData?.hourly?.relative_humidity_2m?.get(11)?.let { Text("${it} %") }

            Spacer(modifier.padding(20.dp))

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
                                painter = painterResource(
                                    id = WmoWeatherCode.fromCode(weatherData!!.hourly.weather_code[index])?.imageRes
                                        ?: R.drawable.clear
                                ),
                                contentDescription = null,
                                modifier = Modifier.size(50.dp)
                            )

                            weatherData?.hourly?.temperature_2m?.get(index)
                                ?.let { Text("${it} °C") }
                            weatherData?.hourly?.relative_humidity_2m?.get(index)
                                ?.let { Text("${it} %") }
                        }
                    }
                }
            }

            Spacer(modifier.padding(20.dp))

            IconToggleButton(

                checked = isFavorite,
                onCheckedChange = {
                    isFavorite = !isFavorite
                }
            ) {
                Icon(
                    tint = if (isFavorite) {
                        Color(0xffE91E63)
                    } else {
                        Color(0xFF000000)
                    },
                    modifier = modifier
                        .graphicsLayer {
                        scaleX = 1.8f
                        scaleY = 1.8f
                    },
                    imageVector = if (isFavorite) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = null
                )
            }
        }
    }
}

