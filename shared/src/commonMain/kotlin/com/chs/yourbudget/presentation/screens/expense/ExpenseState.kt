package com.chs.yourbudget.presentation.screens.expense

import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo

data class ExpenseState(
    val expenseWithPurchaseList: List<Pair<ExpenseInfo, Long>> = emptyList(),
    val isShowDeleteDialog: Boolean = false,
    val targetExpenseInfo: ExpenseInfo? = null
)
