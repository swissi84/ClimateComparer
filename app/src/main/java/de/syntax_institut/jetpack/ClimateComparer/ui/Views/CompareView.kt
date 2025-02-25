package de.syntax_institut.fakeStore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.syntax_institut.jetpack.ClimateComparer.CompareViewModel
import de.syntax_institut.jetpack.ClimateComparer.data.GeoCodeData
import de.syntax_institut.jetpack.ClimateComparer.data.WeatherResponse
import de.syntax_institut.jetpack.ClimateComparer.ui.Views.Components.LocationCard


@Composable
fun CompareView(
    compareViewModel: CompareViewModel,
    onNavigateToWeatherView: (GeoCodeData) -> Unit,
) {

    val weatherData by compareViewModel.weatherDataState.collectAsState()
    val geoCodeData by compareViewModel.geoCodeDataState.collectAsState()

    var searchQuery by remember { mutableStateOf("") }


    Column(modifier = Modifier.padding(16.dp)) {

        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Enter location") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { compareViewModel.loadGeoCode(searchQuery) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))


        if (geoCodeData.isEmpty()) {
            Text("No results found", modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyColumn {
                items(geoCodeData) {  geodata, ->


                    LocationCard(
                        geodata,
                        onClick = { onNavigateToWeatherView(geodata) }
                    )
                }
            }
        }
    }
}




