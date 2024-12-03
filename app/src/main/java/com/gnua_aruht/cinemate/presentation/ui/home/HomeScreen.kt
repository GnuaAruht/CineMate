package com.gnua_aruht.cinemate.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PageSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gnua_aruht.cinemate.R
import com.gnua_aruht.cinemate.data.db.model.Movie
import com.gnua_aruht.cinemate.data.db.model.Trailer


@Composable
fun HomeScreen(
    uiState: HomeUIState,
    trailerPageSize: PageSize,
    movieItemWidth: Dp,
    onMovieClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {

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
        if (uiState.loading && uiState.homeFeed.isEmpty) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
                    .size(68.dp)
            )
        } else {
            HomeBody(
                trailers = uiState.homeFeed.trailers,
                showingMovies = uiState.homeFeed.showingMovies,
                upcomingMovies = uiState.homeFeed.upcomingMovies,
                trailerPageSize = trailerPageSize,
                movieItemWidth = movieItemWidth,
                onMovieClicked = onMovieClicked,
                modifier = Modifier.padding(it)
            )
        }
    }
}

@Composable
private fun HomeBody(
    trailers: List<Trailer>,
    showingMovies: List<Movie>,
    upcomingMovies: List<Movie>,
    trailerPageSize: PageSize,
    movieItemWidth: Dp,
    onMovieClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier,
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
                trailers = trailers,
                onViewAllClicked = {},
            )
        }

        item {
            MoviesWithTitleRow(
                title = "Now Playing",
                movies = showingMovies,
                movieWidth = movieItemWidth,
                onMovieClicked = onMovieClicked,
                onViewAllClicked = {}
            )
        }

        item {
            MoviesWithTitleRow(
                title = "Upcoming",
                movies = upcomingMovies,
                movieWidth = movieItemWidth,
                onMovieClicked = onMovieClicked,
                onViewAllClicked = {}
            )
        }

    }

}