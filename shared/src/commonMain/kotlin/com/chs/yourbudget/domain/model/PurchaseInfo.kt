package com.chs.yourbudget.domain.model

import kotlinx.datetime.LocalDateTime

data class PurchaseInfo(
    val purchaseId: Long,
    val expenseId: Long,
    val userName: String,
    val amount: Long,
    val createAt: LocalDateTime
)
