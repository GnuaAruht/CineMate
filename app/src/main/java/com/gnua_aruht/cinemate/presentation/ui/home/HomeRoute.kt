package com.gnua_aruht.cinemate.presentation.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gnua_aruht.cinemate.presentation.navigation.Route


fun NavGraphBuilder.homeRoute() {
    composable<Route.Home> {
        HomeScreen(modifier = Modifier.fillMaxSize())
    }
}