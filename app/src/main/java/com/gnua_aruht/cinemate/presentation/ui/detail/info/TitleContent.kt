package com.gnua_aruht.cinemate.presentation.ui.detail.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.ratingbar.RatingBar
import com.smarttoolfactory.ratingbar.model.GestureStrategy
import com.smarttoolfactory.ratingbar.model.RateChangeStrategy
import java.time.format.TextStyle


@Composable
internal fun TitleContent(
    title: String,
    rating : Float,
    releaseYear : String,
    runtime : String,
    genres : String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        RatingBar(
            modifier = Modifier
                .padding(bottom = 6.dp)
                .height(32.dp)
                .widthIn(min = 80.dp, max = 140.dp),
            imageVectorFilled = Icons.Rounded.Star,
            imageVectorEmpty = Icons.Rounded.StarOutline,
            rateChangeStrategy = RateChangeStrategy.InstantChange,
            gestureStrategy = GestureStrategy.None,
            tintFilled = MaterialTheme.colorScheme.primary,
            tintEmpty = MaterialTheme.colorScheme.primary,
            itemSize = Dp.Infinity,
            itemCount = 5,
            space = 4.dp,
            rating = rating,
            allowZeroRating = false,
            onRatingChange = {},
        )

        val metaDataTextStyle = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        )

        CompositionLocalProvider(LocalTextStyle provides metaDataTextStyle) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth()
                    .height(intrinsicSize = IntrinsicSize.Min)
            ) {
                Text(releaseYear)
                VerticalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                )
                Text(runtime)
                VerticalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                )
                Text(genres)
            }
        }
    }
}