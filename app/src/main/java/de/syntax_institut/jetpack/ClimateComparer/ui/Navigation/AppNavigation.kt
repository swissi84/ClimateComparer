package de.syntax_institut.jetpack.ClimateComparer.ui.Navigation

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Sailing
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import de.syntax_institut.fakeStore.CompareView
import de.syntax_institut.jetpack.ClimateComparer.CompareViewModel
import de.syntax_institut.jetpack.ClimateComparer.data.Results
import de.syntax_institut.jetpack.ClimateComparer.ui.Views.Components.FullImageBackground
import de.syntax_institut.jetpack.ClimateComparer.ui.Views.HomeView
import de.syntax_institut.jetpack.ClimateComparer.ui.Views.SettingsView
import de.syntax_institut.jetpack.ClimateComparer.ui.Views.WeatherView
import de.syntax_institut.jetpack.ClimateComparer.ui.theme.AppTheme
import kotlinx.serialization.Serializable


@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("SuspiciousIndentation")
@Composable
fun AppNavigation(
    compareViewModel: CompareViewModel = viewModel()
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            FullImageBackground()

            Scaffold(modifier = Modifier
                .fillMaxSize(),
                bottomBar = {
                    NavigationBar(
                        modifier = Modifier
                            .graphicsLayer {
                            shadowElevation = 1.dp.toPx()
                        alpha = 0.9f
                    },
                        containerColor = Color.Transparent,
                        tonalElevation = 0.dp
                    )
                    {
                        NavItem.entries.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = currentBackStackEntry?.destination?.hasRoute(item.route::class)
                                    ?: false,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.label
                                    )
                                },
                                label = {
                                    Text(
                                        text = item.label,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold
                                    ) },
                                )
                        }
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = HomeView,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(innerPadding)
                        .padding(bottom = 10.dp)


                ) {

                    composable<HomeView> {
                        HomeView()
                    }

                    composable<CompareView> {
                        CompareView(
                            compareViewModel = compareViewModel,
                            onNavigateToWeatherView = { results ->
                                navController.navigate(
                                    WeatherViewRoute(
                                        id = results.id,
                                        name = results.name,
                                        latitude = results.latitude,
                                        longitude = results.longitude,
                                        elevation = results.elevation,
                                        feature_code = results.feature_code,
                                        country_code = results.country_code,
                                        timezone = results.timezone,
                                        population = results.population,
                                        country_id = results.country_id,
                                        country = results.country,
                                    )
                                )
                            },
                        )
                    }

                    composable<WeatherViewRoute> {
                      val weatherViewRoute = it.toRoute<(WeatherViewRoute)>()
                        Log.d("WeatherRoute", toString())

                        WeatherView(
                            results = Results(
                                id = weatherViewRoute.id,
                                name = weatherViewRoute.name,
                                latitude = weatherViewRoute.latitude,
                                longitude = weatherViewRoute.longitude,
                                elevation = weatherViewRoute.elevation,
                                feature_code = weatherViewRoute.feature_code,
                                country_code = weatherViewRoute.country_code,
                                timezone = weatherViewRoute.timezone,
                                population = weatherViewRoute.population,
                                country_id = weatherViewRoute.country_id,
                                country = weatherViewRoute.country,
                            ),
                            compareViewModel = compareViewModel,

                        )
                    }

                    composable<SettingsView> {
                        SettingsView()
                    }

                }
            }
        }
    }
}

@Serializable
object HomeView

@Serializable
object CompareView

@Serializable
object SettingsView

@Serializable
object WeatherView


@Serializable
data class WeatherViewRoute(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val elevation: Double = 0.0,
    val feature_code: String = "",
    val country_code: String = "",
    val timezone: String = "",
    val population: Int = 0,
    val country_id: Int = 0,
    val country: String = "",
//    val postcodes: List<String> = emptyList()
)




enum class NavItem(
    val route: Any,
    val label: String,
    val icon: ImageVector,
) {
    First(HomeView, "Home", Icons.Filled.Home),
    Second(CompareView, "Compare", Icons.Filled.Sailing),
    Third(SettingsView, "Settings", Icons.Filled.Settings),
}
