package com.gnua_aruht.cinemate.presentation.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gnua_aruht.cinemate.presentation.ui.home.homeRoute
import com.gnua_aruht.cinemate.presentation.ui.intro.introRoute


@Composable
fun AppNavGraph(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    startDestination : Route,
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        introRoute(
            widthSizeClass = windowSizeClass.widthSizeClass,
            onGetStarted = navController::navigateToHome
        )

        homeRoute()

    }
}