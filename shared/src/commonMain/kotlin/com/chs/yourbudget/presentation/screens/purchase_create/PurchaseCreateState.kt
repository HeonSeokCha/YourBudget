package com.chs.yourbudget.presentation.screens.purchase_create

import com.chs.yourbudget.domain.model.PurchaseInfo
import com.chs.yourbudget.util.toLocalDate
import kotlinx.datetime.LocalDate
import kotlin.time.Clock

data class PurchaseCreateState(
    val title: String? = null,
    val expenseDate: LocalDate = Clock.System.now().toLocalDate(),
    val purchaseList: List<PurchaseInfo> = emptyList(),
    val expandMenuState: Boolean = false,
)
