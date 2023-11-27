package com.example.unitconverter

import AnotherScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    darkTheme: Boolean,
    navController: NavHostController,
    onThemeUpdated: () -> Unit
) {
    var darkTheme by remember { mutableStateOf(false) }
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            MyApp(navController = navController)
        }
        composable(
            route = Screen.About.route
        ) {
            AnotherScreen(
                darkTheme = darkTheme,
                onThemeUpdated = { darkTheme = !darkTheme },
                navController = navController
            )
        }
        composable(
            route = Screen.Converter.route
        ) {
           UnitConverter(navController = navController)
        }
    }
}
