package com.chs.yourbudget.presentation.screens.purchase_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.usecases.GetExpenseWithPurchaseListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class PurchaseViewModel(
    @InjectedParam private val expenseId: Long,
    private val getExpenseWithPurchaseListUseCase: GetExpenseWithPurchaseListUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(PurchaseCreateState())
    val state = _state.asStateFlow()

    init {

    }

    fun clickSave() {
        viewModelScope.launch {

        }
    }
}