package com.chs.yourbudget.domain.model

import kotlinx.datetime.LocalDate

data class ExpenseInfo(
    val expenseId: Long,
    val expenseDate: LocalDate,
    val title: String,
    val memo: String?,
    val createTime: LocalDate,
    val updateTime: LocalDate
)