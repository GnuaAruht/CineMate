package com.gnua_aruht.cinemate.presentation.ui.detail.booking.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gnua_aruht.cinemate.presentation.theme.CineMateTheme
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.Seat
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.SeatInfo
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.SeatInfoData
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.getSeatLayoutForDate


@Composable
fun SeatContent(
    itemSize: Dp,
    seatInfoData: SeatInfoData,
    selectedSeats: List<Seat>,
    onSeatClicked: (Seat) -> Unit,
    modifier: Modifier = Modifier
) {

    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        val maxSize = seatInfoData.data.values.maxOf { it.size }
        val spacing = (maxWidth - (itemSize * maxSize)) / (maxSize + 1)
        val arrangement = Arrangement.spacedBy(spacing)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = arrangement
        ) {
            seatInfoData.data.values.forEach { seatInfoList ->
                SeatRow(
                    itemSize = itemSize,
                    seatInfoList = seatInfoList,
                    selectedSeats = selectedSeats,
                    horizontalArrangement = arrangement,
                    modifier = Modifier.fillMaxWidth(),
                    onSeatClicked = onSeatClicked,
                )
            }

            SeatStatusRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

        }
    }

}


@Composable
fun SeatRow(
    itemSize: Dp,
    seatInfoList: List<SeatInfo>,
    selectedSeats: List<Seat>,
    horizontalArrangement: Arrangement.Horizontal,
    modifier: Modifier = Modifier,
    onSeatClicked: (Seat) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally),
        horizontalArrangement = horizontalArrangement
    ) {
        seatInfoList.forEach { seatInfo ->

            val clickableModifier =
                if (!seatInfo.reserved) {
                    Modifier.clickable { onSeatClicked(seatInfo.seat) }
                } else Modifier

            val color = if (seatInfo.reserved) {
                Color.White.copy(alpha = 0.2f)
            } else {
                if (selectedSeats.any { it.id == seatInfo.seat.id })
                    MaterialTheme.colorScheme.primary
                else Color.White
            }

            Seat(
                color = color,
                modifier = Modifier
                    .size(itemSize)
                    .then(clickableModifier)
                // todo add clickable
            )

        }
    }
}

@Composable
fun SeatStatusRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SeatStatus(label = "Free", color = Color.White)
        SeatStatus(label = "Reserved", color = Color.Gray)
        SeatStatus(label = "Selected", color = MaterialTheme.colorScheme.primary)
    }
}


@Composable
fun SeatStatus(
    color: Color,
    label: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(10.dp)
                .background(color)
        )
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Preview()
@Composable
private fun SeatContentPreview() {
    CineMateTheme {
        SeatContent(
            itemSize = 36.dp,
            seatInfoData = getSeatLayoutForDate("testDate"),
            onSeatClicked = {},
            selectedSeats = emptyList()
        )
    }
}