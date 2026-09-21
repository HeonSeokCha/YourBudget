package com.chs.yourbudget.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.PriceCheck
import androidx.compose.ui.graphics.vector.ImageVector
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

    @Serializable
    data object ScreenUserAmount : BudgetScreens

    @Serializable
    data class ScreenUSerPurchases(val userName: String) : BudgetScreens
}

enum class BottomNavigation(
    val label: String,
    val icon: ImageVector,
    val route: BudgetScreens
) {
    Calendar("Calendar", Icons.Filled.CalendarToday, BudgetScreens.ScreenMain),
    Users("Users", Icons.Filled.PriceCheck, BudgetScreens.ScreenUserAmount),
}
