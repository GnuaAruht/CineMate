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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gnua_aruht.cinemate.presentation.theme.CineMateTheme
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.SeatInfo
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.getDateList
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.getSeatLayoutForDate
import com.gnua_aruht.cinemate.presentation.ui.detail.booking.getTimeList

@Composable
fun BookingContent(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {

    val dateList = rememberSaveable { getDateList(5) }
    val timeList = rememberSaveable { getTimeList() }
    val seatInfoMap = rememberSaveable { mutableMapOf<String, SeatInfo>() }

    var selectedDate by rememberSaveable { mutableStateOf(dateList.first()) }
    var selectedTime by rememberSaveable { mutableStateOf(timeList.first()) }

    val selectedDateTime = "$selectedDate $selectedTime"
    val selectedSeatInfo = seatInfoMap.getOrPut(selectedDateTime) {
        getSeatLayoutForDate(selectedDateTime)
    }

    Scaffold(
        modifier = modifier.fillMaxWidth(),
        containerColor = Color.Transparent,
        topBar = { BookingAppBar(onBackPressed = onBackPressed) },
        content = { paddingValues ->

            Column(modifier = Modifier.padding(paddingValues)) {

                DateSelectionRow(
                    dateStringList = dateList,
                    selectedDate = selectedDate,
                    onDateSelected = { newDate -> selectedDate = newDate },
                    contentPadding = PaddingValues(horizontal = 18.dp, vertical = 4.dp),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
                )

                TimeSelectionRow(
                    timeListString = timeList,
                    selectedTime = selectedTime,
                    onTimeSelected = { newTime -> selectedTime = newTime },
                    contentPadding = PaddingValues(horizontal = 18.dp, vertical = 4.dp),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
                )

//                SeatContent(
//                    seatInfo = selectedSeatInfo,
//                    modifier = Modifier.fillMaxSize()
//                )

            }
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier.fillMaxWidth(), containerColor = Color.Transparent) {
                AppButton(
                    text = "Buy ticket",
                    onClick = {},
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
                "This will be movie name",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            navigationIconContentColor = Color.White,
            containerColor = Color.Transparent
        )
    )

}

@Preview
@Composable
private fun BookingContentPreview() {
    CineMateTheme {
        BookingContent(onBackPressed = {})
    }
}