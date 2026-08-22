package com.chs.yourbudget.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.usecases.GetAllExpenseUseCase
import com.chs.yourbudget.domain.usecases.InsertExpenseUseCase
import com.chs.yourbudget.util.toLocalDate
import com.chs.yourbudget.util.toLocalDateTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.toLocalDateTime
import org.koin.core.annotation.KoinViewModel
import kotlin.time.Clock

@KoinViewModel
class MainViewModel(
    private val getAllExpenseUseCase: GetAllExpenseUseCase,
    private val insertExpenseUseCase: InsertExpenseUseCase,
    private val deleteExpenseUseCase: InsertExpenseUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getAllExpenseUseCase().collect { list ->
                _state.update {
                    it.copy(
                        expenseList = list
                    )
                }
            }
        }
    }

    fun changeDateDialogShow(value: Boolean) {
        _state.update { it.copy(isShowDateDialog = value) }
    }

    fun createExpense(targetDate: Long) {
        viewModelScope.launch {
            insertExpenseUseCase(
                ExpenseInfo(
                    expenseId = 0L,
                    expenseDate = targetDate.toLocalDate(),
                    createTime = Clock.System.now().toEpochMilliseconds().toLocalDateTime(),
                    updateTime = null
                )
            )
        }
    }
}