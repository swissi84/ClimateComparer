package de.syntax_institut.jetpack.ClimateComparer.ui.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.syntax_institut.jetpack.ClimateComparer.R
import de.syntax_institut.jetpack.ClimateComparer.ui.Navigation.SettingsView


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun SettingsView(
    modifier: Modifier = Modifier,
    onBackgroundSelected: (Int) -> Unit
) {
    val backgrounds = listOf(
        R.drawable.background1,
        R.drawable.background2,
        R.drawable.background3
    )

    var selectedBackground by remember { mutableStateOf(backgrounds[0]) }

    Column(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Wähle ein Hintergrundbild", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        LazyRow(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(backgrounds) { background ->
                Image(
                    painter = painterResource(id = background),
                    contentDescription = "Hintergrundbild",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            selectedBackground = background
                            onBackgroundSelected(background)
                        }
                )
            }
        }
    }
}

