package com.gnua_aruht.cinemate.presentation.navigation

import androidx.navigation.NavHostController
import com.gnua_aruht.cinemate.data.db.model.Movie


fun NavHostController.navigateToHome() {
    navigate(Route.Home) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
    }
}

fun NavHostController.navigateToDetail(movie : Movie) {
    navigate(Route.Detail(movie))
}
