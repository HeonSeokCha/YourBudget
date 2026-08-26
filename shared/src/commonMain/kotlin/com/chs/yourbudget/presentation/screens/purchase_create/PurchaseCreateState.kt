package com.chs.yourbudget.presentation.screens.purchase_create

data class PurchaseCreateState(
    val expandMenuState: Boolean = false,
    val userName: String? = null,
    val memo: String? = null,
    val amount: Long = 0L
)
