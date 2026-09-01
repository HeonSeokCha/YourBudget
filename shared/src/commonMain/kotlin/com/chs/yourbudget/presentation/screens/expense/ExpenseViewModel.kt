package com.chs.yourbudget.presentation.screens.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.usecases.DeleteExpenseWithPurchaseUseCase
import com.chs.yourbudget.domain.usecases.GetExpenseListWithPurchasesUseCase
import com.chs.yourbudget.domain.usecases.GetExpenseWithPurchasesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class ExpenseViewModel(
    @InjectedParam private val targetDate: Long,
    private val getExpenseListWithPurchasesUseCase: GetExpenseListWithPurchasesUseCase,
    private val deleteExpenseWithPurchaseUseCase: DeleteExpenseWithPurchaseUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ExpenseState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getExpenseListWithPurchasesUseCase(targetDate).collect { info ->
                _state.update {
                    it.copy(expenseWithPurchaseList = info)
                }
            }
        }
    }

    fun setDeleteExpenseInfo(expenseInfo: ExpenseInfo) {
        _state.update { it.copy(targetExpenseInfo = expenseInfo) }
    }

    fun changeShowDeleteDialog(value: Boolean) {
        _state.update { it.copy(isShowDeleteDialog = value) }
    }

    fun deleteExpenseInfo() {
        if (state.value.targetExpenseInfo == null) return
        viewModelScope.launch {
            deleteExpenseWithPurchaseUseCase(state.value.targetExpenseInfo!!.expenseId)
        }

        _state.update {
            it.copy(
                isShowDeleteDialog = false,
                targetExpenseInfo = null
            )
        }
    }
}