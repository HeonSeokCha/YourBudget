package com.chs.yourbudget.presentation.screens.user_purchases

import com.chs.yourbudget.domain.model.PurchaseInfo
import kotlinx.datetime.LocalDate

data class UserPurchasesState(
    val userName: String? = null,
    val purchaseList: Map<LocalDate, List<PurchaseInfo>> = emptyMap()
)
