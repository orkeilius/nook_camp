package com.example.kncr.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock


fun getCurrentTimeText(): String {
    val now : LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val hours = now.hour.toString().padStart(2, '0')
    val minutes = now.minute.toString().padStart(2, '0')
    return "$hours:$minutes"
}

fun getMillisToNextMinute(): Long {
    val now : LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val secondsLeft = 60 - now.second
    val millisLeft = 1000 - (Clock.System.now().toEpochMilliseconds() % 1000)
    return secondsLeft * 1000L + millisLeft
}
