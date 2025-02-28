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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.syntax_institut.jetpack.ClimateComparer.CompareViewModel
import de.syntax_institut.jetpack.ClimateComparer.WeatherComponents.WmoWeatherCode
import de.syntax_institut.jetpack.ClimateComparer.data.local.FavoriteLocation
import de.syntax_institut.jetpack.ClimateComparer.ui.ViewModel.HomeViewModel
import java.util.Calendar

@Composable
fun HomeView(
    homeViewModel: HomeViewModel,
    compareViewModel: CompareViewModel,
    modifier: Modifier = Modifier,
) {

    val savedLocations = remember { mutableStateOf<List<FavoriteLocation>>(emptyList()) }
    val weatherData by compareViewModel.weatherDataState.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getSavedFavoriteLocations().collect { locations ->
            savedLocations.value = locations
            if (locations.isNotEmpty()) {
                compareViewModel.loadWeatherData(
                    latitude = locations[0].latitude,
                    longitude = locations[0].longitude,
                )
            }
        }
    }

    if (savedLocations.value.isNotEmpty()) {
        val calendar = Calendar.getInstance()
            val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
            val weather = WmoWeatherCode.fromCode(
                weatherData?.hourly?.weather_code?.get(currentHour) ?: 0
            ) ?: WmoWeatherCode.CLEAR_SKY

            Box(
                modifier = Modifier
                    .padding(60.dp)
                    .padding(vertical = 50.dp)
                    .background(
                        Color.Black.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(16.dp)
                    ),

                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = savedLocations.value[0].name,
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White
                    )

                    Image(
                        painter = painterResource(id = weather.imageRes),
                        contentDescription = weather.description,
                        modifier = Modifier.size(150.dp)
                    )

                    Text(weather.description, fontSize = 20.sp, color = Color.White)

                    Spacer(modifier.padding(20.dp))

                    Text("Temperatur", color = Color.White)
                    weatherData?.hourly?.temperature_2m?.get(currentHour)
                        ?.let { Text("${it} °C", color = Color.White) }

                    Spacer(modifier.padding(20.dp))

                    Text("Luftfeuchtigkeit", color = Color.White)
                    weatherData?.hourly?.relative_humidity_2m?.get(currentHour)
                        ?.let { Text("${it} %", color = Color.White) }
                }
            }


    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Kein gespeicherter Standort gefunden", color = Color.White)
        }
    }
}


