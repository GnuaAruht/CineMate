package com.gnua_aruht.cinemate.presentation.ui.intro

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gnua_aruht.cinemate.presentation.navigation.Route


fun NavGraphBuilder.introRoute(
    widthSizeClass: WindowWidthSizeClass,
    onGetStarted: () -> Unit,
    modifier: Modifier = Modifier
) {
    composable<Route.Intro> {
        val viewModel = hiltViewModel<IntroViewModel>()
        IntroScreen(
            onGetStarted = {
                viewModel.saveAppHasLaunched()
                onGetStarted()
            },
            modifier = modifier.fillMaxSize()
        )
    }

}