package com.chs.yourbudget.presentation.screens.update_purchase

import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo
import com.chs.yourbudget.util.Constants
import kotlinx.datetime.LocalDate

data class UpdatePurchaseState(
    val expenseInfo: ExpenseInfo? = null,
    val expenseTitle: String? = null,
    val expenseTargetDate: LocalDate? = null,
    val purchaseList: List<PurchaseInfo> = emptyList(),
    val isShowDeleteDialog: Boolean = false,
    val isShowAddDialog: Boolean = false,
    val targetPurchase: PurchaseInfo? = null,
    val purchaseUserName: String = Constants.USER_NAME_LIST.first(),
    val purchaseAmount: Long = 0L
)
