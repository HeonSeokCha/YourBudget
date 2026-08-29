package com.chs.yourbudget.presentation.screens.create_expense

import com.chs.yourbudget.util.toLocalDate
import kotlinx.datetime.LocalDate
import kotlin.time.Clock

data class CreateExpenseState(
    val title: String? = null,
    val expenseDate: LocalDate = Clock.System.now().toLocalDate(),
    val purchaseList: MutableList<Pair<String, Long>> = mutableListOf(),
    val isShowDateDialog: Boolean = false
)
