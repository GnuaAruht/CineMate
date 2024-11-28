package com.gnua_aruht.cinemate.presentation.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.gnua_aruht.cinemate.data.db.model.Movie
import com.gnua_aruht.cinemate.presentation.ui.detail.info.InfoContent


@Composable
fun DetailScreen(
    movie: Movie,
    onBackPressed: () -> Unit,
    widthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {

    val gradientColors = listOf(Color.Transparent, MaterialTheme.colorScheme.background)
    val blurRadius by remember { mutableStateOf(0.dp) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/w500/${movie.poster}")
                .crossfade(true).build(),
            contentScale = ContentScale.Companion.Crop,
            contentDescription = "Poster",
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.68f)
                .blur(radius = blurRadius)
                .drawWithCache {
                    val gradient = Brush.radialGradient(
                        colors = gradientColors,
                        center = Offset(x = size.width / 2, y = size.height / 2.8f),
                        radius = size.width / 1.46f,
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(brush = gradient, alpha = 0.98f)
                    }
                },
        )
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.2f))
                .blur(radius = blurRadius)
        )

        InfoContent(
            movie = movie,
            onBackPressed = onBackPressed,
            onBuyTicketPressed = {},
            modifier = Modifier.fillMaxSize()
        )

    }

}