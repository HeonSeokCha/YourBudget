package com.chs.yourbudget.presentation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.chs.yourbudget.presentation.screens.expense.ExpenseScreen
import com.chs.yourbudget.presentation.screens.expense.ExpenseViewModel
import com.chs.yourbudget.presentation.screens.main.MainScreen
import com.chs.yourbudget.presentation.screens.main.MainViewModel
import com.chs.yourbudget.presentation.screens.purchase_create.PurchaseCreateScreen
import com.chs.yourbudget.presentation.screens.purchase_create.PurchaseViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainNavDisplay(
    modifier: Modifier = Modifier,
    backStack: SnapshotStateList<BudgetScreens>,
) {
    NavDisplay(
        modifier = modifier
            .fillMaxSize(),
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { it }) togetherWith slideOutHorizontally(
                targetOffsetX = { -it })
        },
        popTransitionSpec = {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(
                targetOffsetX = { it })
        },
        predictivePopTransitionSpec = {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(
                targetOffsetX = { it })
        },
        entryProvider = entryProvider {
            entry<BudgetScreens.ScreenMain> {
                val viewModel = koinViewModel<MainViewModel>()
                MainScreen(
                    viewModel = viewModel,
                    onClickExpense = {
                        backStack.add(BudgetScreens.ScreenExpense(it))
                    }
                )
            }

            entry<BudgetScreens.ScreenExpense> {
                val viewModel = koinViewModel<ExpenseViewModel> {
                    parametersOf(it.expenseId)
                }
                ExpenseScreen(
                    viewModel = viewModel,
                    onClickCreatePurchase = {
                        backStack.add(BudgetScreens.ScreenPurchaseCreate(it))
                    }
                )
            }

            entry<BudgetScreens.ScreenPurchaseCreate> {
                val viewModel = koinViewModel<PurchaseViewModel> {
                    parametersOf(it.expenseId)
                }
                PurchaseCreateScreen(viewModel) {
                    backStack.removeLastOrNull()
                }
            }
        }
    )
}