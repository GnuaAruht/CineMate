package com.gnua_aruht.cinemate.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Typography


@Composable
fun CineMateTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = Typography(),
        colorScheme = AppColorScheme,
        content = content
    )
}