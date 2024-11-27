package com.gnua_aruht.cinemate.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.gnua_aruht.cinemate.R
import com.smarttoolfactory.ratingbar.RatingBar
import com.smarttoolfactory.ratingbar.model.GestureStrategy
import com.smarttoolfactory.ratingbar.model.RateChangeStrategy


@Composable
fun MoviesWithTitleRow(
    title: String,
    movieWidth: Dp,
    onViewAllClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TitleRow(
            title = title,
            onLabelClicked = onViewAllClicked,
            modifier = Modifier.fillMaxWidth()
        )
        MovieRows(
            onMovieClicked = {},
            movieWidth = movieWidth,
        )
    }


}

@Composable
fun MovieRows(
    movieWidth: Dp,
    onMovieClicked: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = dimensionResource(R.dimen.default_padding)),
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .layout { measurable, constraints ->
                val padding = contentPadding.calculateLeftPadding(LayoutDirection.Ltr)
                val newMaxWidth = constraints.maxWidth + (padding * 2).roundToPx()
                val newConstraints = constraints.copy(maxWidth = newMaxWidth)
                val placeable = measurable.measure(newConstraints)
                layout(placeable.width, placeable.height) {
                    placeable.place(0, 0)
                }
            }
        ,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = contentPadding
    ) {
        items(7) {
            MovieCard(
                modifier = Modifier
                    .width(movieWidth)
                    .aspectRatio(0.54f)
                    .clickable(
                        onClick = {  },
                        interactionSource = null,
                        indication = null
                    )
            )
        }

    }
}


@Composable
fun MovieCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium)
                .background(Color.White)
        )
        Text(
            text = "Movie title",
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        RatingBar(
            modifier = Modifier
                .heightIn(min = 24.dp, max = 32.dp)
                .fillMaxWidth(),
            imageVectorFilled = Icons.Rounded.Star,
            imageVectorEmpty = Icons.Rounded.StarOutline,
            rateChangeStrategy = RateChangeStrategy.InstantChange,
            gestureStrategy = GestureStrategy.None,
            tintFilled = MaterialTheme.colorScheme.primary,
            tintEmpty = MaterialTheme.colorScheme.primary,
            itemSize = Dp.Infinity,
            itemCount = 5,
            space = 4.dp,
            rating = 3.4f,
            allowZeroRating = false,
            onRatingChange = {}
        )
    }
}