package com.example.finalapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun SettingsScreen(navController: NavHostController, userId: Long) {
    Column() {
        Text("Settings - Usuário com ID ${userId}")
    }
}

@Composable
fun Setting() {

}