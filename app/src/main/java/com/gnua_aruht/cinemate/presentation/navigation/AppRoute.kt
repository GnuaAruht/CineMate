package com.gnua_aruht.cinemate.presentation.navigation

import com.gnua_aruht.cinemate.data.db.model.Movie
import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object Intro : Route()

    @Serializable
    data object Home : Route()

    @Serializable
    data class Detail(val movie : Movie) : Route()
}