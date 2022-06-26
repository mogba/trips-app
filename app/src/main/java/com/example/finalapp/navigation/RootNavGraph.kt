package com.example.finalapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalapp.layouts.MainLayout

@Composable
fun SetupNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AUTH_ROUTE,
        route = ROOT_ROUTE,
    ) {
        authNavGraph(navController = navController)

        composable(HOME_ROUTE) {
            MainLayout()
        }
    }
}