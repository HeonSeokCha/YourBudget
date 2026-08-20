package com.chs.yourbudget.presentation.screens.expense

import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo

data class ExpenseState(
    val expenseInfo: ExpenseInfo? = null,
    val purchaseList: List<PurchaseInfo> = emptyList()
)
