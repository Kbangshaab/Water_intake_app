package com.example.attempt4

sealed class Screen(val route: String) {
    object HomeScreenOptions : Screen("home_screen")
    object WaterScreen : Screen("water_screen")
    object WeightScreen : Screen("weight_screen")
    object CaloriesScreen : Screen("calories_screen")
}

