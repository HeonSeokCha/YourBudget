package com.chs.yourbudget.util

import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.byValue
import androidx.compose.foundation.text.input.insert
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.then
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

fun Instant.toLocalDate(): LocalDate {
    return this.toEpochMilliseconds().toLocalDateTime().date
}


fun Instant.toLocalDateTime(): LocalDateTime {
    return this.toEpochMilliseconds().toLocalDateTime()
}

fun Long.toCommaString(): String {
    val isNegative = this < 0
    val absStr = kotlin.math.abs(this).toString()
    val formatted = absStr.reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()

    return if (isNegative) "-$formatted" else formatted
}

val digitsOnlyInputTransformation: InputTransformation =
    InputTransformation.byValue { _, proposed ->
        proposed.filter { it.isDigit() }
    }.then(InputTransformation.maxLength(12))

object MoneyOutputTransformation : OutputTransformation {
    override fun TextFieldBuffer.transformOutput() {
        var index = length - 3
        while (index > 0) {
            insert(index, ",")
            index -= 3
        }
    }
}
