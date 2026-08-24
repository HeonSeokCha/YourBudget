package com.chs.yourbudget.presentation.screens.main

import com.chs.yourbudget.domain.model.ExpenseInfo

data class MainState(
    val expenseList: List<ExpenseInfo> = emptyList(),
    val isShowDateDialog: Boolean = false,
    val isShowDeleteDialog: Boolean = false,
    val targetExpenseInfo: ExpenseInfo? = null
)