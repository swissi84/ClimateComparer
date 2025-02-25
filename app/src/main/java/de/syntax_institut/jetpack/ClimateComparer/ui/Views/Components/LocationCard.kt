package de.syntax_institut.jetpack.ClimateComparer.ui.Views.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.syntax_institut.jetpack.ClimateComparer.data.GeoCodeData

@Composable
fun LocationCard(result: GeoCodeData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick


        ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Name: ${result.name}", fontWeight = FontWeight.Bold)
            Text(text = "Country: ${result.country} (${result.country_code})")
            Text(text = "Latitude: ${result.latitude}, Longitude: ${result.longitude}")
            Text(text = "Timezone: ${result.timezone}")
            Text(text = "Population: ${result.population}")
            if (result.postcodes.isNotEmpty()) {
                Text(text = "Postcodes: ${result.postcodes.joinToString()}")
            }
        }
    }
}