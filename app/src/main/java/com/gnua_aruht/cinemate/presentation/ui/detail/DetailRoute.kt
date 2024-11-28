package com.gnua_aruht.cinemate.presentation.ui.detail

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.gnua_aruht.cinemate.data.db.model.Movie
import com.gnua_aruht.cinemate.presentation.navigation.CustomNavType
import com.gnua_aruht.cinemate.presentation.navigation.Route
import kotlin.reflect.typeOf


fun NavGraphBuilder.detailRoute(
    widthSizeClass: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    composable<Route.Detail>(typeMap = mapOf(typeOf<Movie>() to CustomNavType.MovieType)) {
        val arguments = it.toRoute<Route.Detail>()
        DetailScreen(
            movie = arguments.movie,
            onBackPressed = onBackPressed,
            widthSizeClass = widthSizeClass,
            modifier = modifier,
        )
    }
}