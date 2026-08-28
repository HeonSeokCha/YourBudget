package com.chs.yourbudget.presentation.screens.update_purchase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.usecases.GetExpenseWithPurchaseListUseCase
import com.chs.yourbudget.domain.usecases.InsertExpenseUseCase
import com.chs.yourbudget.domain.usecases.InsertPurchaseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class UpdatePurchaseViewModel(
    @InjectedParam private val expenseId: Long,
    private val getExpenseWithPurchaseListUseCase: GetExpenseWithPurchaseListUseCase,
    private val insertPurchaseUseCase: InsertPurchaseUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(UpdatePurchaseState())
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


    fun changeExpandState(value: Boolean) {
        _state.update { it.copy(expandMenuState = value) }
    }

    fun clickSave() {

    }
}