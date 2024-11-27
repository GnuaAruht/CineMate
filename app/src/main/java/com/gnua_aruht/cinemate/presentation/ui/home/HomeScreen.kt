package com.gnua_aruht.cinemate.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PageSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.gnua_aruht.cinemate.R


@Composable
fun HomeScreen(
    widthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {

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
    val movieWidth = if (widthSizeClass == WindowWidthSizeClass.Expanded) 180.dp else 128.dp

    Scaffold(
        topBar = {
            HomeAppBar(
                onMenuClicked = {},
                onSearchClicked = {},
                onNotificationClicked = {},
                onAvatarClicked = {},
            )
        },
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
            state = rememberLazyListState(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_xlarge)),
            contentPadding = PaddingValues(
                top = dimensionResource(R.dimen.default_padding),
                start = dimensionResource(R.dimen.default_padding),
                end = dimensionResource(R.dimen.default_padding),
                bottom = 100.dp,
            )
        ) {

            item {
                TrailersWithTitleRow(
                    pageSize = trailerPageSize,
                    onViewAllClicked = {},
                )
            }

            item {
                MoviesWithTitleRow(
                    title = "Now Playing",
                    movieWidth = movieWidth,
                    onViewAllClicked = {}
                )
            }

            item {
                MoviesWithTitleRow(
                    title = "Upcoming",
                    movieWidth = movieWidth,
                    onViewAllClicked = {}
                )
            }

        }
    }

}