package com.gnua_aruht.cinemate.presentation.ui.home

import PlayButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.gnua_aruht.cinemate.BuildConfig
import com.gnua_aruht.cinemate.R
import com.gnua_aruht.cinemate.data.db.model.Trailer

@Composable
fun TrailersWithTitleRow(
    pageSize: PageSize,
    trailers : List<Trailer>,
    onViewAllClicked: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = dimensionResource(R.dimen.default_padding)),
    pageSpacing: Dp = dimensionResource(R.dimen.padding_medium),
) {
    val pagerState = rememberPagerState(pageCount = { trailers.size })
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        TitleRow(
            title = "Trailers",
            onLabelClicked = onViewAllClicked,
            modifier = Modifier.fillMaxWidth()
        )
        HorizontalPager(
            state = pagerState,
            pageSize = pageSize,
            contentPadding = contentPadding,
            modifier = Modifier.layout { measurable, constraints ->
                val padding = contentPadding.calculateLeftPadding(LayoutDirection.Ltr)
                val newMaxWidth = constraints.maxWidth + (padding * 2).roundToPx()
                val newConstraints = constraints.copy(maxWidth = newMaxWidth)
                val placeable = measurable.measure(newConstraints)
                layout(placeable.width, placeable.height) {
                    placeable.place(0, 0)
                }
            },
            pageSpacing = pageSpacing
        ) {
            TrailerItem(
                trailer = trailers[it],
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1.8f)
            )
        }
    }

}

@Composable
fun TrailerItem(
    trailer: Trailer,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data("${BuildConfig.IMAGE_URL}${trailer.poster}")
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Companion.Crop,
            contentDescription = "Icon",
            modifier = modifier.fillMaxSize().clip(MaterialTheme.shapes.medium))
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.2f))
        )
        PlayButton(modifier = Modifier.size(74.dp))
    }
}
