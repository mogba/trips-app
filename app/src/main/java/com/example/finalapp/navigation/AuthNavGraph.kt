package com.example.finalapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.finalapp.screens.LoginScreen
import com.example.finalapp.screens.RegisterScreen
import com.example.finalapp.screens.ResetPasswordScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = ScreenManager.Login.route,
        route = AUTH_ROUTE
    ) {
        composable(ScreenManager.Login.route) {
            LoginScreen(navController)
        }
        composable(ScreenManager.Register.route) {
            RegisterScreen(navController)
        }
        composable(ScreenManager.ResetPassword.route) {
            ResetPasswordScreen(navController)
        }
    }
}