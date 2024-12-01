package com.gnua_aruht.cinemate.presentation.ui.detail.booking

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

data class Seat(
    val id: String,
    val price: Float,
)

data class SeatInfo(
    val reserved: Boolean = false,
    val seat: Seat
)

fun getSeatRowFor(
    prefixId: String,
    price: Float,
    count: Int,
): List<SeatInfo> {
    return List(count) {
        SeatInfo(
            seat = Seat(
                id = "$prefixId${it + 1}",
                price = price
            ),
            reserved = Random.nextBoolean()
        )
    }
}


data class SeatInfoData(
    val id: String,
    val data: Map<String, List<SeatInfo>> = mapOf(
        "A" to getSeatRowFor(prefixId = "A", price = 20f, count = 5),
        "B" to getSeatRowFor(prefixId = "B", price = 18f, count = 7),
        "C" to getSeatRowFor(prefixId = "C", price = 16f, count = 8),
        "D" to getSeatRowFor(prefixId = "D", price = 14f, count = 7),
        "E" to getSeatRowFor(prefixId = "E", price = 12f, count = 8),
        "F" to getSeatRowFor(prefixId = "F", price = 10f, count = 5)
    )
)


fun getSeatLayoutForDate(dateTimeString: String): SeatInfoData {
    return SeatInfoData(id = dateTimeString)
}

fun getDateList(count: Int, startFrom: LocalDate = LocalDate.now()): List<String> {
    val dateStringList = mutableListOf<String>()
    var date = startFrom
    val formatter = DateTimeFormatter.ofPattern("EEE-d")
    repeat(count) {
        val dateString = formatter.format(date)
        dateStringList.add(dateString)
        date = date.plusDays(1)
    }
    return dateStringList
}


fun getTimeList(): List<String> {
    return listOf("11:30 AM", "12:50 PM", "1:30 PM", "2:50 PM", "3:30 PM")
}


