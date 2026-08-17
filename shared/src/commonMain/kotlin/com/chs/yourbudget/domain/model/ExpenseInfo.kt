package com.chs.yourbudget.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class ExpenseInfo(
    val expenseId: Long,
    val expenseDate: LocalDate,
    val createTime: LocalDateTime,
    val updateTime: LocalDateTime?
)