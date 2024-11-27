package com.gnua_aruht.cinemate.presentation.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gnua_aruht.cinemate.presentation.navigation.Route


fun NavGraphBuilder.homeRoute(
    widthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    composable<Route.Home> {
        HomeScreen(
            widthSizeClass = widthSizeClass,
            modifier = modifier.fillMaxSize()
        )
    }
}