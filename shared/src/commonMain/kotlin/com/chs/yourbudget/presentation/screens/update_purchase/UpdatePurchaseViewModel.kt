package com.chs.yourbudget.presentation.screens.update_purchase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.usecases.DeletePurchaseUseCase
import com.chs.yourbudget.domain.usecases.GetExpenseWithPurchasesUseCase
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
    private val getExpenseWithPurchasesUseCase: GetExpenseWithPurchasesUseCase,
    private val insertPurchaseUseCase: InsertPurchaseUseCase,
    private val deletePurchaseUseCase: DeletePurchaseUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UpdatePurchaseState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                val info = getExpenseWithPurchasesUseCase(expenseId)
                it.copy(
                    expenseInfo = info.first,
                    purchaseList = info.second
                )
            }
        }
    }

    fun changeExpandState(value: Boolean) {
        _state.update { it.copy(expandMenuState = value) }
    }

    fun clickSave() {

    }
}