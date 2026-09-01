package com.chs.yourbudget.presentation.screens.expense

import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo

data class ExpenseState(
    val expenseWithPurchaseList: List<Pair<ExpenseInfo, List<PurchaseInfo>>> = emptyList(),
    val isShowDeleteDialog: Boolean = false,
    val targetExpenseInfo: ExpenseInfo? = null
)
