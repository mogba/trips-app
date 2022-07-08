package com.example.finalapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.finalapp.layouts.MainLayout
import com.example.finalapp.screens.TripsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AUTH_ROUTE,
        route = ROOT_ROUTE,
    ) {
        authNavGraph(navController = navController)

        composable(
            //HOME_ROUTE,
            "home/{userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.LongType
                },
            ),
        ) { backStackEntry ->
            MainLayout(
                backStackEntry.arguments?.getLong("tripId") ?: 0
            )
        }
    }
}