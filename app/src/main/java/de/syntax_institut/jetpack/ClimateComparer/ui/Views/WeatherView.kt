package de.syntax_institut.jetpack.ClimateComparer.ui.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.syntax_institut.jetpack.ClimateComparer.CompareViewModel
import de.syntax_institut.jetpack.ClimateComparer.data.Results


@Composable
fun WeatherView(
    compareViewModel: CompareViewModel,
    results: Results,
    modifier: Modifier = Modifier,
) {

     LaunchedEffect(results.latitude, results.longitude) {
         compareViewModel.loadWeatherData(
             latitude = results.latitude,
             longitude = results.longitude,
         )
     }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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


        }
    }

}