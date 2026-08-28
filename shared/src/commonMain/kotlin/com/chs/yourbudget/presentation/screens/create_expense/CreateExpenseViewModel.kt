package com.chs.yourbudget.presentation.screens.create_expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.usecases.GetExpenseWithPurchaseListUseCase
import com.chs.yourbudget.domain.usecases.InsertExpenseUseCase
import com.chs.yourbudget.domain.usecases.InsertPurchaseUseCase
import com.chs.yourbudget.util.toLocalDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class CreateExpenseViewModel(
    @InjectedParam private val expenseId: Long,
    private val getExpenseWithPurchaseListUseCase: GetExpenseWithPurchaseListUseCase,
    private val insertPurchaseUseCase: InsertPurchaseUseCase,
    private val insertExpenseUseCase: InsertExpenseUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CreateExpenseState())
    val state = _state.asStateFlow()

    init {
        if (expenseId != 0L) {
            viewModelScope.launch {
                val info = getExpenseWithPurchaseListUseCase(expenseId)
                _state.update {
                    it.copy(
                        expenseInfo = info.first,
                        purchaseList = info.second
                    )
                }
            }
        } else {
        }
    }


    fun changeShowDateDialog(value: Boolean) {
        _state.update { it.copy(isShowDateDialog = value) }
    }

    fun updateExpenseDate(milli: Long) {
        _state.update { it.copy(expenseDate = milli.toLocalDate()) }
    }

    fun clickSave() {

    }
}