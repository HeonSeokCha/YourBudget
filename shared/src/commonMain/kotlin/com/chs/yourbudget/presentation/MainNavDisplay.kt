package com.chs.yourbudget.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.chs.yourbudget.presentation.screens.expense.ExpenseScreen
import com.chs.yourbudget.presentation.screens.main.MainScreen
import com.chs.yourbudget.presentation.screens.purchase.PurchaseScreen

@Composable
fun MainNavDisplay(
    backStack: SnapshotStateList<BudgetScreens>,
) {
    NavDisplay(
        modifier = Modifier
            .fillMaxSize(),
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<BudgetScreens.ScreenMain> {
                MainScreen()
            }

            entry<BudgetScreens.ScreenExpense> {
                ExpenseScreen()
            }

            entry<BudgetScreens.ScreenPurchase> {
                PurchaseScreen()
            }
        }
    )
}