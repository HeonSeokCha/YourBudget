package com.chs.yourbudget.presentation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface BudgetScreens: NavKey {
    @Serializable
    data object ScreenMain : BudgetScreens

    @Serializable
    data class ScreenExpense(val targetDate: Long) : BudgetScreens

    @Serializable
    data class ScreenPurchase(val expenseId: Long) : BudgetScreens
}