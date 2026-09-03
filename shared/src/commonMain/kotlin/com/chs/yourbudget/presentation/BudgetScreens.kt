package com.chs.yourbudget.presentation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface BudgetScreens: NavKey {
    @Serializable
    data object ScreenMain : BudgetScreens

    @Serializable
    data class ScreenExpense(val expenseDate: Long) : BudgetScreens

    @Serializable
    data object ScreenExpenseCreate : BudgetScreens

    @Serializable
    data class ScreenPurchaseUpdate(val expenseId: Long) : BudgetScreens
}