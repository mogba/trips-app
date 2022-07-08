package com.example.finalapp.layouts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.finalapp.navigation.BottomBarNavGraph
import com.example.finalapp.navigation.ScreenManager
import com.example.finalapp.navigation.ScreenManagerComponent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainLayout(userId: Long) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarItems = listOf(
        ScreenManagerComponent.Home,
        ScreenManagerComponent.Trips,
        ScreenManagerComponent.Settings
    )

    Scaffold(
        floatingActionButton = {
            if (currentDestination?.route == ScreenManagerComponent.Trips.route) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(ScreenManager.TripForm.routeWithArgs())
                    },
                ) {
                    Icon(Icons.Filled.Add, null)
                }
            }
        },
        bottomBar = {
            BottomNavigation {
                bottomBarItems.forEach { screen ->
                    AddBottomNavigationItem(screen, currentDestination, navController)
                }
            }
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomBarNavGraph(navController = navController, userId)
        }
    }
}

@Composable
fun RowScope.AddBottomNavigationItem(
    screen: ScreenManagerComponent,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    BottomNavigationItem(
        icon = {
            Icon(screen.icon, contentDescription = null)
        },
        label = {
            Text(stringResource(screen.resourceId))
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}
