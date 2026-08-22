package com.chs.yourbudget.presentation.screens.expense

import androidx.lifecycle.ViewModel
import com.chs.yourbudget.domain.usecases.GetExpenseWithPurchaseListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class ExpenseViewModel(
    @InjectedParam private val expenseId: Long,
    private val getExpenseUseCase: GetExpenseWithPurchaseListUseCase,
    private val getExpenseWithPurchaseListUseCase: GetExpenseWithPurchaseListUseCase
): ViewModel() {
    private val _state = MutableStateFlow(ExpenseState())
    val state = _state.asStateFlow()
}