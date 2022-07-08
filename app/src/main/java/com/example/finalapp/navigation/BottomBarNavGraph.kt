package com.example.finalapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.finalapp.screens.HomeScreen
import com.example.finalapp.screens.SettingsScreen
import com.example.finalapp.screens.TripsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomBarNavGraph(navController: NavHostController, userId: Long) {
    NavHost(
        navController = navController,
        startDestination = ScreenManager.Home.route,
        route = HOME_ROUTE,
    ) {
        composable(
            ScreenManager.Home.route,
        ) {
            HomeScreen(
                navController,
                0
            )
        }
        composable(
            ScreenManager.Settings.route,
        ) {
            SettingsScreen(
                navController,
                0
            )
        }

        tripsNavGraph(navController, userId)
    }
}