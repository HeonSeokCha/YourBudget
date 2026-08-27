package com.chs.yourbudget.presentation.screens.purchase_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.model.PurchaseInfo
import com.chs.yourbudget.domain.usecases.GetExpenseWithPurchaseListUseCase
import com.chs.yourbudget.domain.usecases.InsertExpenseUseCase
import com.chs.yourbudget.domain.usecases.InsertPurchaseUseCase
import com.chs.yourbudget.util.toLocalDateTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel
import kotlin.time.Clock

@KoinViewModel
class PurchaseViewModel(
    @InjectedParam private val expenseId: Long,
    private val getExpenseWithPurchaseListUseCase: GetExpenseWithPurchaseListUseCase,
    private val insertPurchaseUseCase: InsertPurchaseUseCase,
    private val insertExpenseUseCase: InsertExpenseUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(PurchaseCreateState())
    val state = _state.asStateFlow()

    fun changeExpandState(value: Boolean) {
        _state.update { it.copy(expandMenuState = value) }
    }

    fun clickSave() {

    }
}