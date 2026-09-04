package com.chs.yourbudget.presentation.screens.main

import com.chs.yourbudget.domain.model.ExpenseInfo
import kotlinx.datetime.LocalDate

data class MainState(
    val expenseList: List<Pair<LocalDate, Long>> = listOf()
)