package com.example.finalapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.finalapp.screens.TripFormScreen
import com.example.finalapp.screens.TripsScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.tripsNavGraph(navController: NavHostController, userId: Long) {
    navigation(
        startDestination = ScreenManager.Trips.route,
        route = TRIPS_ROUTE,
    ) {
        composable(
            ScreenManager.Trips.route
        ) {
            TripsScreen(
                navController,
                0
            )
        }
        composable(
            ScreenManager.TripForm.route,
            arguments = listOf(
                navArgument("tripId") {
                    type = NavType.LongType
                },
            ),
        ) { backStackEntry ->
            TripFormScreen(
                navController,
                backStackEntry.arguments?.getLong("tripId") ?: 0
            )
        }
    }
}