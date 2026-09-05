package com.chs.yourbudget.presentation.screens.update_purchase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.model.PurchaseInfo
import com.chs.yourbudget.domain.usecases.DeletePurchaseUseCase
import com.chs.yourbudget.domain.usecases.GetExpenseWithPurchasesUseCase
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
    private val getExpenseWithPurchasesUseCase: GetExpenseWithPurchasesUseCase,
    private val insertPurchaseUseCase: InsertPurchaseUseCase,
    private val deletePurchaseUseCase: DeletePurchaseUseCase,
    private val insertExpenseUseCase: InsertExpenseUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UpdatePurchaseState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                val info = getExpenseWithPurchasesUseCase(expenseId)
                val expenseInfo = info.keys.first()
                val purchaseList = info.values.first()
                it.copy(
                    expenseInfo = expenseInfo,
                    purchaseList = purchaseList,
                    title = expenseInfo.title,
                    targetDate = expenseInfo.expenseDate
                )
            }
        }
    }

    fun changeExpandState(value: Boolean) {
        _state.update { it.copy(expandMenuState = value) }
    }

    fun changeStateFromDeleteDialog(
        value: Boolean,
        purchaseInfo: PurchaseInfo? = null
    ) {
        _state.update {
            it.copy(
                isShowDeleteDialog = value,
                targetPurchase = purchaseInfo
            )
        }
    }

    fun clickSave() {
        if (_state.value.expenseInfo == null) return
        viewModelScope.launch {
            _state.value.expenseInfo!!.copy(
                title = _state.value.title!!,
                expenseDate = _state.value.targetDate!!
            ).run {
                insertExpenseUseCase(this)
            }
        }
    }
}