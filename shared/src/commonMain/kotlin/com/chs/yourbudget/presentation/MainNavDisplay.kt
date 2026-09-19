package com.chs.yourbudget.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.chs.yourbudget.presentation.screens.bottom.BottomTopLevelBackStack
import com.chs.yourbudget.presentation.screens.create_expense.CreateExpenseScreen
import com.chs.yourbudget.presentation.screens.expense.ExpenseScreen
import com.chs.yourbudget.presentation.screens.expense.ExpenseViewModel
import com.chs.yourbudget.presentation.screens.main.MainScreen
import com.chs.yourbudget.presentation.screens.main.MainViewModel
import com.chs.yourbudget.presentation.screens.create_expense.CreateExpenseViewModel
import com.chs.yourbudget.presentation.screens.update_purchase.UpdatePurchaseScreen
import com.chs.yourbudget.presentation.screens.update_purchase.UpdatePurchaseViewModel
import com.chs.yourbudget.presentation.screens.user_amount.UserAmountScreen
import com.chs.yourbudget.presentation.screens.user_amount.UserAmountViewModel
import com.chs.yourbudget.util.NavDirection
import com.chs.yourbudget.util.directionalTransform
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainNavDisplay(
    modifier: Modifier = Modifier,
    backStack: BottomTopLevelBackStack,
) {
    NavDisplay(
        modifier = modifier
            .fillMaxSize(),
        backStack = backStack.backStack,
        onBack = { backStack.removeLast() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        transitionSpec = { directionalTransform(backStack.direction) },
        popTransitionSpec = { directionalTransform(backStack.direction) },
        predictivePopTransitionSpec = { directionalTransform(NavDirection.BACKWARD) },
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
                    parametersOf(it.expenseDate)
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
                    backStack.removeLast()
                }
            }

            entry<BudgetScreens.ScreenPurchaseUpdate> {
                val viewModel = koinViewModel<UpdatePurchaseViewModel> {
                    parametersOf(it.expenseId)
                }

                UpdatePurchaseScreen(viewModel) {
                    backStack.removeLast()
                }
            }

            entry<BudgetScreens.ScreenUserAmount> {
                val viewModel = koinViewModel<UserAmountViewModel>()

                UserAmountScreen(viewModel)
            }
        }
    )
}