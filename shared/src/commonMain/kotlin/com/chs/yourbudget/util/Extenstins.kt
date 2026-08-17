package com.chs.yourbudget.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun LocalDate.toMillis(): Long {
    return this.atTime(0, 0, 0)
        .toInstant(TimeZone.currentSystemDefault())
        .toEpochMilliseconds()
}

fun LocalDateTime.toMillis(): Long {
    return this.toInstant(TimeZone.currentSystemDefault())
        .toEpochMilliseconds()
}

fun Long.toLocalDateTime(): LocalDateTime {
    return Instant.fromEpochMilliseconds(this)
        .toLocalDateTime(TimeZone.currentSystemDefault())
}

fun Long.toLocalDate(): LocalDate {
    return this.toLocalDateTime().date
}
