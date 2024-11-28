package com.gnua_aruht.cinemate.presentation.ui.detail.info

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun InfoAppBar(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                )
            }
        },
        title = {},
        colors = TopAppBarDefaults.topAppBarColors(
            navigationIconContentColor = Color.White,
            containerColor = Color.Transparent
        )
    )
}