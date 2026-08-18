package com.chs.yourbudget.presentation.screens.expense

import com.chs.yourbudget.domain.model.ExpenseInfo

data class ExpenseState(
    val expenseList: List<ExpenseInfo> = emptyList()
)
