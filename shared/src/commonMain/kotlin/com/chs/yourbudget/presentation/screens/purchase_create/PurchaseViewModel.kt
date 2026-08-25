package com.chs.yourbudget.presentation.screens.purchase_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.model.PurchaseInfo
import com.chs.yourbudget.domain.usecases.GetExpenseWithPurchaseListUseCase
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
    private val insertPurchaseUseCase: InsertPurchaseUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(PurchaseCreateState())
    val state = _state.asStateFlow()

    fun changeExpandState(value: Boolean) {
        _state.update { it.copy(expandMenuState = value) }
    }

    fun changeUserName(userName: String) {
        if (_state.value.userName == userName) {
            _state.update { it.copy(userName = null, expandMenuState = false) }
        } else {
            _state.update { it.copy(userName = userName, expandMenuState = false) }
        }
    }

    fun clickSave() {
        if (_state.value.userName == null) return

        viewModelScope.launch {
            insertPurchaseUseCase(
                PurchaseInfo(
                    purchaseId = 0,
                    expenseId = expenseId,
                    userName = _state.value.userName!!,
                    amount = _state.value.amount,
                    createAt = Clock.System.now().toEpochMilliseconds().toLocalDateTime()
                )
            )
        }
    }
}