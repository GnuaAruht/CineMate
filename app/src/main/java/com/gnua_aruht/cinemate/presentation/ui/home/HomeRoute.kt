package com.gnua_aruht.cinemate.presentation.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gnua_aruht.cinemate.presentation.navigation.Route


fun NavGraphBuilder.homeRoute(
    widthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {

    composable<Route.Home> {

        val viewModel = hiltViewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val trailerPageSize = remember(widthSizeClass) {
            object : PageSize {
                override fun Density.calculateMainAxisPageSize(
                    availableSpace: Int,
                    pageSpacing: Int
                ): Int {
                    return when (widthSizeClass) {
                        WindowWidthSizeClass.Expanded -> (availableSpace - (2 * pageSpacing)) / 2
                        else -> availableSpace - pageSpacing
                    }
                }
            }
        }

        HomeScreen(
            uiState = uiState,
            trailerPageSize = trailerPageSize,
            movieItemWidth = if (widthSizeClass == WindowWidthSizeClass.Expanded) 180.dp else 120.dp,
            modifier = modifier.fillMaxSize(),
        )

    }
}