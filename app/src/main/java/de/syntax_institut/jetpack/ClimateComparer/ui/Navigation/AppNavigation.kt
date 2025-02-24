package de.syntax_institut.jetpack.ClimateComparer.ui.Navigation

import android.annotation.SuppressLint
import android.os.Build
import android.widget.ImageView
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.VideoLibrary
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
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import de.syntax_institut.fakeStore.SearchView
import de.syntax_institut.jetpack.ClimateComparer.ui.Views.Components.FullImageBackground
import de.syntax_institut.jetpack.ClimateComparer.ui.Views.HomeView
import de.syntax_institut.jetpack.ClimateComparer.ui.Views.SettingsView
import de.syntax_institut.jetpack.ClimateComparer.ui.theme.AppTheme
import kotlinx.serialization.Serializable


@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("SuspiciousIndentation")
@Composable
fun AppNavigation() {
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

                    composable<SearchView> {
                        SearchView()
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
object SearchView

@Serializable
object SettingsView

enum class NavItem(
    val route: Any,
    val label: String,
    val icon: ImageVector,
) {
    First(HomeView, "Home", Icons.Filled.Home),
    Second(SearchView, "Image", Icons.Filled.Image),
    Third(SettingsView, "Video", Icons.Filled.VideoLibrary),
}
