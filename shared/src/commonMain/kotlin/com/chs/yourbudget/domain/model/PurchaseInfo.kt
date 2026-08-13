package com.chs.yourbudget.domain.model

data class PurchaseInfo(
    val purchaseId: Long,
    val expenseId: Long,
    val userName: String,
    val amount: Long,
)
