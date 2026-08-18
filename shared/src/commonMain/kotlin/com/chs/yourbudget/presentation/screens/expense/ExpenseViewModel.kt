package com.chs.yourbudget.presentation.screens.expense

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.chs.yourbudget.domain.usecases.GetAllExpenseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class ExpenseViewModel(
    private val getAllExpenseUseCase: GetAllExpenseUseCase
): ViewModel() {
    private val _state = MutableStateFlow(ExpenseState())
    val state = _state.asStateFlow()
}