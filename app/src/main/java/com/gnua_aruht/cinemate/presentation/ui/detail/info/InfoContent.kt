package com.gnua_aruht.cinemate.presentation.ui.detail.info

import AppButton
import PlayButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gnua_aruht.cinemate.data.db.model.Movie

@Composable
fun InfoContent(
    movie: Movie,
    widthSizeClass: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
    onBuyTicketPressed: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent,
        topBar = { InfoAppBar(onBackPressed = onBackPressed) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding(),
                        start = 18.dp,
                        end = 18.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(space = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(2.4f))
                PlayButton(modifier = Modifier.size(74.dp))
                TitleContent(
                    title = movie.title,
                    rating = movie.rating,
                    releaseYear = movie.releaseYear,
                    runtime = movie.runtime,
                    genres = movie.genres,
                    modifier = Modifier.fillMaxWidth()
                )
                OverViewContent(
                    overview = movie.overview,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = Color.Transparent
            ) {
                AppButton(
                    text = "Buy ticket",
                    onClick = onBuyTicketPressed,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth()
                        .fillMaxWidth(fraction = 0.76f)
                )
            }
        }
    )
}