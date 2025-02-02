package com.example.spot.presentation.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph (
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost (
        navController = navController,
        startDestination = startDestination
    ) {
        navigation (
            route = Route.SpotNavigation.route,
            startDestination = Route.SpotNavigator.route
        ) {
            composable (
                route = Route.SpotNavigator.route
            ) {
                SpotNavigation()
            }
        }
    }
}