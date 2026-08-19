package com.chs.yourbudget.presentation.screens.purchase_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.usecases.GetPurchaseFromDate
import com.chs.yourbudget.presentation.screens.purchase.PurchaseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class PurchaseViewModel(
    @InjectedParam private val expenseId: Long,
    private val getPurchaseFromDate: GetPurchaseFromDate,
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