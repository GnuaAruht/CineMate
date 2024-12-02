package com.gnua_aruht.cinemate.presentation.ui.detail.booking

import AppButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.gnua_aruht.cinemate.R
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.components.DateSelectionRow
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.components.SeatContent
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.components.SeatLayout
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.components.TimeSelectionRow
import java.text.NumberFormat
import java.util.Locale

private val Int.toCurrency : String
    get() {
        val format = NumberFormat.getCurrencyInstance(Locale.US).apply {
            maximumFractionDigits = 0
        }
        return format.format(this)
    }

@Composable
fun BookingContent(
    title: String,
    widthSizeClass: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {

    val dateList = rememberSaveable { getDateList(5) }
    val timeList = rememberSaveable { getTimeList() }
    val seatInfoDataMap = rememberSaveable { mutableMapOf<String, SeatInfoData>() }

    var selectedDate by rememberSaveable { mutableStateOf(dateList.first()) }
    var selectedTime by rememberSaveable { mutableStateOf(timeList.first()) }

    val selectedDateTime = "$selectedDate $selectedTime"
    val selectedSeatInfoData = seatInfoDataMap.getOrPut(selectedDateTime) {
        getSeatLayoutForDate(selectedDateTime)
    }
    val selectedSeatList = remember { mutableStateListOf<Seat>() }
    val totalCost = selectedSeatList.sumOf { it.price }

    Scaffold(
        modifier = modifier.fillMaxWidth(),
        containerColor = Color.Transparent,
        topBar = {
            BookingAppBar(
                title = title,
                onBackPressed = onBackPressed
            )
        },
        content = { paddingValues ->

            Column(modifier = Modifier.padding(paddingValues)) {

                DateSelectionRow(
                    dateStringList = dateList,
                    selectedDate = selectedDate,
                    onDateSelected = { newDate ->
                        selectedDate = newDate
                        selectedSeatList.clear() // reset selected seats
                    },
                    contentPadding = PaddingValues(horizontal = 18.dp, vertical = 4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )

                TimeSelectionRow(
                    timeListString = timeList,
                    selectedTime = selectedTime,
                    onTimeSelected = { newTime ->
                        selectedTime = newTime
                        selectedSeatList.clear() // reset selected seats
                    },
                    contentPadding = PaddingValues(horizontal = 18.dp, vertical = 4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                SeatLayout(modifier = Modifier.fillMaxWidth()) { paddingValues ->
                    SeatContent(
                        itemSize = 36.dp, // todo update item size according to window width
                        seatInfoData = selectedSeatInfoData,
                        selectedSeats = selectedSeatList,
                        onSeatSelected = { selected, newSeat ->
                            if (selected) {
                                selectedSeatList.add(newSeat)
                            } else {
                                selectedSeatList.remove(newSeat)
                            }
                        },
                        modifier = Modifier.padding(paddingValues)
                    )
                }

            }
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier.fillMaxWidth(), containerColor = Color.Transparent) {
                AppButton(
                    text = "Buy ticket ${if (totalCost > 0) totalCost.toCurrency else ""}",
                    onClick = {},
                    enabled = totalCost > 0,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth()
                        .fillMaxWidth(fraction = 0.76f)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookingAppBar(
    title: String,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                )
            }
        },
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            navigationIconContentColor = Color.White,
            containerColor = Color.Transparent
        )
    )

}
