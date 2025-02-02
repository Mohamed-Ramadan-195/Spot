package com.example.spot.presentation.navigation.nav_graph

sealed class Route (
    val route: String
) {
    // Screens
    data object HomeScreen: Route(route = "home_screen")
    data object SearchScreen: Route(route = "search_screen")
    data object BookmarkScreen: Route(route = "bookmark_screen")

    // Navigator
    data object SpotNavigation: Route(route = "spot_navigation")
    data object SpotNavigator: Route(route = "spot_navigator")
}