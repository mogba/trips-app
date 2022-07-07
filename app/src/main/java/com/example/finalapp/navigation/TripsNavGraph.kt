package com.example.finalapp.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.finalapp.screens.TripFormScreen
import com.example.finalapp.screens.TripsScreen

fun NavGraphBuilder.tripsNavGraph(navController: NavHostController) {
    navigation(
        startDestination = ScreenManager.Trips.route,
        route = TRIPS_ROUTE,
    ) {
        composable(ScreenManager.Trips.route) {
            TripsScreen(navController, 0)
        }
        composable(
            ScreenManager.TripForm.route,
            arguments = listOf(navArgument("tripId") { defaultValue = "0" }),
        ) { backStackEntry ->
            TripFormScreen(navController, backStackEntry.arguments?.getInt("tripId") ?: 0)
        }
    }
}