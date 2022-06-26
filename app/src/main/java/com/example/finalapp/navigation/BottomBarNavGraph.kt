package com.example.finalapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalapp.screens.HomeScreen
import com.example.finalapp.screens.SettingsScreen

@Composable
fun BottomBarNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenManager.Home.route,
        route = HOME_ROUTE,
    ) {
        composable(ScreenManager.Home.route) {
            HomeScreen(navController, null)
        }
        composable(ScreenManager.Settings.route) {
            SettingsScreen(navController, null)
        }

        tripsNavGraph(navController)
    }
}