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
import com.chs.yourbudget.presentation.screens.create_expense.CreateExpenseScreen
import com.chs.yourbudget.presentation.screens.expense.ExpenseScreen
import com.chs.yourbudget.presentation.screens.expense.ExpenseViewModel
import com.chs.yourbudget.presentation.screens.main.MainScreen
import com.chs.yourbudget.presentation.screens.main.MainViewModel
import com.chs.yourbudget.presentation.screens.create_expense.CreateExpenseViewModel
import com.chs.yourbudget.presentation.screens.update_purchase.UpdatePurchaseScreen
import com.chs.yourbudget.presentation.screens.update_purchase.UpdatePurchaseViewModel
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
                    },
                    onClickCreateExpense = {
                        backStack.add(BudgetScreens.ScreenExpenseCreate)
                    }
                )
            }

            entry<BudgetScreens.ScreenExpense> {
                val viewModel = koinViewModel<ExpenseViewModel> {
                    parametersOf(it.expenseId)
                }
                ExpenseScreen(
                    viewModel = viewModel,
                    onClickUpdateExpense = {
                        backStack.add(BudgetScreens.ScreenPurchaseUpdate(it))
                    }
                )
            }

            entry<BudgetScreens.ScreenExpenseCreate> {
                val viewModel = koinViewModel<CreateExpenseViewModel>()
                CreateExpenseScreen(viewModel) {
                    backStack.removeLastOrNull()
                }
            }

            entry<BudgetScreens.ScreenPurchaseUpdate> {
                val viewModel = koinViewModel<UpdatePurchaseViewModel> {
                    parametersOf(it.expenseId)
                }

                UpdatePurchaseScreen(viewModel) {
                    backStack.removeLastOrNull()
                }
            }
        }
    )
}