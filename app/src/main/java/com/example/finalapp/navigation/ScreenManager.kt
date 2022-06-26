package com.example.finalapp.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import com.example.finalapp.R
import androidx.compose.ui.graphics.vector.ImageVector

const val ROOT_ROUTE = "root"
const val AUTH_ROUTE = "auth"
const val HOME_ROUTE = "home"
const val TRIPS_ROUTE = "trips"

sealed class ScreenManager(
    val route: String,
) {
    object Login: ScreenManager("login_screen")
    object Register: ScreenManager("register_screen")
    object ResetPassword: ScreenManager("resetpassword_screen")

    object Home: ScreenManager("home_screen")
    object Trips: ScreenManager("trips_screen")
    object TripForm: ScreenManager("tripform_screen?tripId={tripId}") {
        fun routeWithArgs(tripId: Int?) = route.replace("{tripId}", "$tripId")
    }
    object Settings: ScreenManager("settings_screen")
}

sealed class ScreenManagerComponent(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object Home: ScreenManagerComponent(ScreenManager.Home.route, R.string.home, Icons.Filled.Home)
    object Trips: ScreenManagerComponent(ScreenManager.Trips.route, R.string.trips, Icons.Filled.Face)
    object Settings: ScreenManagerComponent(ScreenManager.Settings.route, R.string.settings, Icons.Filled.Build)
}
