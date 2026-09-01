package com.chs.yourbudget.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class ExpenseInfo(
    val expenseId: Long = 0L,
    val title: String,
    val expenseDate: LocalDate,
    val createTime: LocalDateTime,
    val updateTime: LocalDateTime? = null
)