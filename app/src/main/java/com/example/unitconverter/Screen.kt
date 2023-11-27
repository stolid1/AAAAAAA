package com.example.unitconverter

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Converter: Screen(route = "converter_screen")
    object About: Screen(route = "about_screen")
}
