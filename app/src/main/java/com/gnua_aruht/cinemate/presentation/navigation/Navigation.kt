package com.gnua_aruht.cinemate.presentation.navigation

import androidx.navigation.NavHostController


fun NavHostController.navigateToHome() {
    navigate(Route.Home) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
    }
}

fun NavHostController.navigateToDetail() {
    navigate(Route.Detail)
}
