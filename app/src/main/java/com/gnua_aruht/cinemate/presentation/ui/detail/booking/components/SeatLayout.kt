package com.gnua_aruht.cinemate.presentation.ui.detail.booking.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gnua_aruht.cinemate.presentation.theme.CineMateTheme
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.Seat
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.SeatInfoData
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.getSeatLayoutForDate

@Composable
fun SeatLayout(
    itemSize: Dp,
    seatInfoData: SeatInfoData,
    selectedSeats : List<Seat>,
    onSeatClicked: (Seat) -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {

        ProjectorBg(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp, max = 140.dp)
        )

        SeatContent(
            itemSize = itemSize,
            seatInfoData = seatInfoData,
            selectedSeats = selectedSeats,
            onSeatClicked = onSeatClicked,
            modifier = Modifier.padding(top = 88.dp),
        )

    }

}

@Preview
@Composable
private fun SeatLayoutPreview() {
    CineMateTheme {
        SeatLayout(
            itemSize = 36.dp,
            seatInfoData = getSeatLayoutForDate("testDate"),
            onSeatClicked = {},
            selectedSeats = emptyList()
        )
    }
}