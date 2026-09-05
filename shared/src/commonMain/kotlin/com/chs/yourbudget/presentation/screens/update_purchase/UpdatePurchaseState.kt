package com.chs.yourbudget.presentation.screens.update_purchase

import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo
import kotlinx.datetime.LocalDate

data class UpdatePurchaseState(
    val expenseInfo: ExpenseInfo? = null,
    val purchaseList: List<PurchaseInfo> = emptyList(),
    val expandMenuState: Boolean = false,
    val isShowDeleteDialog: Boolean = false,
    val targetPurchase: PurchaseInfo? = null,
    val title: String? = null,
    val targetDate: LocalDate? = null
)
