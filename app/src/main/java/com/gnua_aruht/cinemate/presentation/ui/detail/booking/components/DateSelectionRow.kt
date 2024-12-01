package com.gnua_aruht.cinemate.presentation.ui.detail.booking.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DateSelectionRow(
    dateStringList: List<String>,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {

//    val animatedValues = List(dateStringList.size) { remember { Animatable(0f) } }
//    val offsetValues = List(dateStringList.size) { remember { Animatable(100f) } }
//
//    LaunchedEffect(Unit) {
//        launch {
//            animatedValues.forEachIndexed { index, animatable ->
//                animatable.animateTo(
//                    targetValue = 1f,
//                    animationSpec = tween(durationMillis = 60, delayMillis = index * 10)
//                )
//            }
//        }
//        launch {
//            offsetValues.forEachIndexed { index, animatable ->
//                animatable.animateTo(
//                    targetValue = 0f,
//                    animationSpec = tween(durationMillis = 60, delayMillis = index * 10)
//                )
//            }
//        }
//    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        items(dateStringList.size) { index ->
            val date = dateStringList[index]
            DatePickerCard(
                dateString = date,
                isSelected = date == selectedDate,
                modifier = Modifier
                    .width(76.dp)
                    .clickable(
                        onClick = { onDateSelected(date) },
                        interactionSource = null,
                        indication = null
                    )
            )
        }
    }

}


@Composable
private fun rememberDatePickerShape(
    cornerShapeDp: Dp,
    arcShapeDp: Dp,
    holeRadiusDp: Dp
): DatePickerShape {

    val cornerShape = with(LocalDensity.current) { cornerShapeDp.toPx() }
    val arcShape = with(LocalDensity.current) { arcShapeDp.toPx() }
    val holeRadius = with(LocalDensity.current) { holeRadiusDp.toPx() }

    return remember(cornerShape, arcShape, holeRadius) {
        DatePickerShape(
            cornerShape = cornerShape,
            arcShape = arcShape,
            holeRadius = holeRadius
        )
    }
}


@Composable
fun DatePickerCard(
    dateString: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    cornerShapeDp: Dp = 18.dp,
    arcShapeDp: Dp = 4.8.dp,
    holeRadiusDp: Dp = 6.dp
) {

    val weekdayAndDay = dateString.split("-")
    val weekday = weekdayAndDay.first()
    val day = weekdayAndDay.last()

    val cardShape = rememberDatePickerShape(
        cornerShapeDp,
        if (isSelected) arcShapeDp else 0.dp,
        holeRadiusDp
    )
    val textColor = if (isSelected) Color.Black else Color.White

    Surface(
        modifier = modifier
            .aspectRatio(0.72f)
            .graphicsLayer {
                clip = true
                shape = cardShape
            },
        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.White.copy(alpha = 0.38f),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = weekday, color = textColor)
            Text(
                text = day,
                color = textColor,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.weight(0.6f))
        }
    }

}
