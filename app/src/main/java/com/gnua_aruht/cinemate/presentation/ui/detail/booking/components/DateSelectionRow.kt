package com.gnua_aruht.cinemate.presentation.ui.detail.booking.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gnua_aruht.cinemate.R
import com.gnua_aruht.cinemate.presentation.theme.CineMateTheme
import com.gnua_aruht.cinemate.presentation.theme.primaryColor
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.getDateList

@Composable
private fun rememberDatePickerShape(
    selected: Boolean = false,
    widthSizeClass: WindowWidthSizeClass,
): DatePickerShape {

    val cornerShape = with(LocalDensity.current) {
        when (widthSizeClass) {
            WindowWidthSizeClass.Compact -> 18.dp.toPx()
            else -> 24.dp.toPx()
        }
    }

    val arcShape = with(LocalDensity.current) {
        when (widthSizeClass) {
            WindowWidthSizeClass.Compact -> 4.8.dp.toPx()
            else -> 6.8.dp.toPx()
        }
    }

    val holeRadius = with(LocalDensity.current) {
        when (widthSizeClass) {
            WindowWidthSizeClass.Compact -> 6.dp.toPx()
            else -> 6.8.dp.toPx()
        }
    }

    return remember(widthSizeClass,selected) {
        DatePickerShape(
            cornerShape = cornerShape,
            holeRadius = holeRadius,
            arcShape = if(selected) arcShape else 0f
        )
    }
}

@Composable
fun DateSelectionRow(
    dateStringList: List<String>,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact
) {

    val selectedDatePickerShape = rememberDatePickerShape(selected = true, widthSizeClass = widthSizeClass)
    val datePickerShape = rememberDatePickerShape(widthSizeClass = widthSizeClass)

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.default_padding)),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(dateStringList) { dateString ->
            val isSelected = selectedDate == dateString
            DatePickerCard(
                dateString = dateString,
                isSelected = isSelected,
                modifier = Modifier
                    .width(if (widthSizeClass == WindowWidthSizeClass.Compact) 76.dp else 92.dp)
                    .aspectRatio(0.68f)
                    .graphicsLayer {
                        clip = true
                        shape = if (isSelected) selectedDatePickerShape else datePickerShape
                    }
                    .clickable(
                        onClick = { onDateSelected(dateString) },
                        interactionSource = null,
                        indication = null
                    )
            )
        }
    }
}


@Composable
fun DatePickerCard(
    dateString: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    val (weekday, day) = dateString.split("-")
    Surface(
        modifier = modifier,
        color = if (isSelected) primaryColor else Color.White.copy(alpha = 0.38f),
        contentColor = if (isSelected) Color.Black else Color.White,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(fraction = 0.18f))
            Text(text = weekday)
            Text(
                text = day,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview
@Composable
private fun DatePickerRowPreview() {
    CineMateTheme {
        val dateList = getDateList(5)
        DateSelectionRow(
            dateStringList = dateList,
            selectedDate = dateList.first(),
            onDateSelected = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}