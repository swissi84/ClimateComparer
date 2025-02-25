package de.syntax_institut.jetpack.ClimateComparer

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import de.syntax_institut.jetpack.ClimateComparer.data.WeatherResponse
import de.syntax_institut.jetpack.ClimateComparer.ui.Navigation.AppNavigation


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()

                }
            }
        }




