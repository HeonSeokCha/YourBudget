package com.chs.yourbudget.presentation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface BudgetScreens: NavKey {
    @Serializable
    data object ScreenMain : BudgetScreens

    @Serializable
    data object ScreenExpenseCreate : BudgetScreens

    @Serializable
    data class ScreenExpense(val expenseId: Long) : BudgetScreens

    @Serializable
    data class ScreenPurchaseCreate(val expenseId: Long) : BudgetScreens
}