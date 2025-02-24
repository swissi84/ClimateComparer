package de.syntax_institut.jetpack.ClimateComparer.ui.Views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.syntax_institut.jetpack.ClimateComparer.ui.Navigation.SettingsView


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun SettingsView(
    modifier: Modifier = Modifier,

) {
    Column(
        modifier = Modifier
            .padding(vertical = 6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
    }
}

