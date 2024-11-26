package com.gnua_aruht.cinemate.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object Intro : Route()

    @Serializable
    data object Home : Route()

    @Serializable
    data object Detail : Route()
}