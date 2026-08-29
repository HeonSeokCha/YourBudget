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
    private val insertPurchaseUseCase: InsertPurchaseUseCase,
    private val insertExpenseUseCase: InsertExpenseUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CreateExpenseState())
    val state = _state.asStateFlow()

    fun changeShowDateDialog(value: Boolean) {
        _state.update { it.copy(isShowDateDialog = value) }
    }

    fun updateExpenseDate(milli: Long) {
        _state.update { it.copy(expenseDate = milli.toLocalDate()) }
    }

    fun updateExpenseTitle(title: String) {
        _state.update { it.copy(title = title) }
    }

    fun updatePurchaseList(info: Pair<String, Long>) {
        _state.update {
            it.copy(
                purchaseList = it.purchaseList.apply { this.add(info) }
            )
        }
    }

    fun removePurchaseList(idx: Int) {
        _state.update {
            it.copy(
                purchaseList = it.purchaseList.apply { this.removeAt(idx) }
            )
        }
    }

    fun clickSave() {

    }
}