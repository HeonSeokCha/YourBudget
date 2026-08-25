package com.chs.yourbudget.presentation.screens.purchase_create

import com.chs.yourbudget.util.Constants

data class PurchaseCreateState(
    val expandMenuState: Boolean = false,
    val userName: String? = null,
    val memo: String? = null,
    val amount: Long = 0L
)
