package com.chs.yourbudget.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.usecases.GetAllExpenseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val getAllExpenseUseCase: GetAllExpenseUseCase,

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

    fun changeDeleteDialogShow(value: Boolean) {
        if (!value) {
            _state.update { it.copy(targetExpenseInfo = null) }
        }
        _state.update { it.copy(isShowDeleteDialog = value) }
    }

    fun targetExpense(expenseInfo: ExpenseInfo) {
        _state.update { it.copy(targetExpenseInfo = expenseInfo) }
    }

    fun createExpense(targetDate: Long) {
        viewModelScope.launch {
//            insertExpenseUseCase(
//                ExpenseInfo(
//                    expenseId = 0L,
//                    expenseDate = targetDate.toLocalDate(),
//                    createTime = Clock.System.now().toEpochMilliseconds().toLocalDateTime(),
//                    updateTime = null
//                )
//            )
        }
    }


}