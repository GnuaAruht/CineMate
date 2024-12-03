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
import com.gnua_aruht.cinemate.data.db.model.Movie
import com.gnua_aruht.cinemate.presentation.navigation.Route
import kotlin.math.roundToInt


fun NavGraphBuilder.homeRoute(
    widthSizeClass: WindowWidthSizeClass,
    onMovieClicked : (Movie) -> Unit,
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
                        WindowWidthSizeClass.Compact -> availableSpace - pageSpacing
                        else -> ((availableSpace - pageSpacing) / 1.4f).roundToInt()
                    }
                }
            }
        }
        val movieItemWidth = if (widthSizeClass == WindowWidthSizeClass.Compact) 130.dp else 168.dp
        HomeScreen(
            uiState = uiState,
            trailerPageSize = trailerPageSize,
            movieItemWidth = movieItemWidth,
            onMovieClicked = onMovieClicked,
            modifier = modifier.fillMaxSize(),
        )

    }
}