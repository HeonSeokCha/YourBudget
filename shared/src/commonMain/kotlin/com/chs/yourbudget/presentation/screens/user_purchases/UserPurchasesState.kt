package com.chs.yourbudget.presentation.screens.user_purchases

import com.chs.yourbudget.domain.model.PurchaseInfo

data class UserPurchasesState(
    val userName: String? = null,
    val purchaseList: List<PurchaseInfo> = emptyList()
)
