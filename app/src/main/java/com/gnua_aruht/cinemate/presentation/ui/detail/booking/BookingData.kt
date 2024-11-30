package com.gnua_aruht.cinemate.presentation.ui.detail.booking

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

data class Seat(
    val id: String,
    val price: Float,
    val reserved: Boolean = false
)

fun getSeatRowFor(
    prefixId: String,
    price: Float,
    count: Int,
): List<Seat> {
    return List(count) {
        Seat(
            id = "$prefixId${it + 1}",
            price = price,
            reserved = Random.nextBoolean()
        )
    }
}

data class SeatInfo(
    val id : String,
    val aRow: List<Seat> = getSeatRowFor(prefixId = "A", price = 20f, count = 5),
    val bRow: List<Seat> = getSeatRowFor(prefixId = "B", price = 18f, count = 7),
    val cRow: List<Seat> = getSeatRowFor(prefixId = "C", price = 16f, count = 8),
    val dRow: List<Seat> = getSeatRowFor(prefixId = "D", price = 14f, count = 7),
    val eRow: List<Seat> = getSeatRowFor(prefixId = "E", price = 12f, count = 8),
    val fRow: List<Seat> = getSeatRowFor(prefixId = "F", price = 10f, count = 5),
)


fun getSeatLayoutForDate(dateTimeString: String): SeatInfo {
    return SeatInfo(id = dateTimeString)
}

fun getDateList(count: Int,startFrom : LocalDate = LocalDate.now()): List<String> {
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

fun getTimeList() : List<String> {
    return listOf("11:30 AM", "12:50 PM", "1:30 PM", "2:50 PM", "3:30 PM")
}


