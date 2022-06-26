package com.example.finalapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.finalapp.navigation.*

@Composable
fun HomeScreen(navController: NavHostController, userId: Int?) {
    Column() {
        Text(text = "Home")

        Button(
            onClick = { navController.navigateUp() }
        ) {
            Text(text = "Voltar")
        }
    }
}
