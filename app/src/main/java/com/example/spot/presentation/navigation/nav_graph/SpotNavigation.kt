package com.example.spot.presentation.navigation.nav_graph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.spot.R
import com.example.spot.presentation.navigation.bottom_nav.BottomNavigationItem
import com.example.spot.presentation.navigation.bottom_nav.SpotBottomNavigation
import com.example.spot.presentation.screen.bookmark.BookmarkScreen
import com.example.spot.presentation.screen.home.HomeScreen
import com.example.spot.presentation.screen.search.SearchScreen

@Composable
fun SpotNavigation () {
    val bottomNavigationItemData = remember {
        listOf (
            BottomNavigationItem (
                title = "Home",
                icon = R.drawable.ic_home
            ),
            BottomNavigationItem (
                title = "Search",
                icon = R.drawable.ic_search
            ),
            BottomNavigationItem (
                title = "Bookmark",
                icon = R.drawable.ic_bookmark
            )
        )
    }

    val navController = rememberNavController()
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    var selectedItem by remember { mutableIntStateOf(0) }

    // when click back button, the selected item will be updated
    selectedItem = remember(key1 = currentBackStackEntry) {
        when (currentBackStackEntry?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    // bottom navigation bar visible on the screen
    val isBottomBarVisible = remember(key1 = currentBackStackEntry) {
        currentBackStackEntry?.destination?.route == Route.HomeScreen.route ||
        currentBackStackEntry?.destination?.route == Route.SearchScreen.route ||
        currentBackStackEntry?.destination?.route == Route.BookmarkScreen.route
    }

    // Bottom Navigation Bar
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                SpotBottomNavigation (
                    items = bottomNavigationItemData,
                    onItemSelected = selectedItem,
                    onItemClicked = { index ->
                        when (index) {
                            0 -> navigateToTap (
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTap (
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateToTap (
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost (
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable (route = Route.HomeScreen.route) {
                HomeScreen()
            }
            composable (route = Route.SearchScreen.route) {
                SearchScreen()
            }
            composable (route = Route.BookmarkScreen.route) {
                BookmarkScreen()
            }
        }
    }
}

private fun navigateToTap (
    navController: NavController,
    route: String
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}