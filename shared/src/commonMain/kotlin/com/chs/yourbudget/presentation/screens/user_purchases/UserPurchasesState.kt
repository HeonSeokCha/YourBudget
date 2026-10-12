package com.chs.yourbudget.presentation.screens.user_purchases

import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo
import kotlinx.datetime.LocalDate

data class UserPurchasesState(
    val userName: String? = null,
    val purchaseList: List<Pair<LocalDate, List<Pair<ExpenseInfo, List<PurchaseInfo>>>>> = listOf()
)
