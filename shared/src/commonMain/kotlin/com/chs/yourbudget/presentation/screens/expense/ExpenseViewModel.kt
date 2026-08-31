package com.chs.yourbudget.presentation.screens.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.model.PurchaseInfo
import com.chs.yourbudget.domain.usecases.DeleteExpenseWithPurchaseUseCase
import com.chs.yourbudget.domain.usecases.DeletePurchaseUseCase
import com.chs.yourbudget.domain.usecases.GetExpenseWithPurchaseListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class ExpenseViewModel(
    @InjectedParam private val expenseId: Long,
    private val getExpenseWithPurchaseListUseCase: GetExpenseWithPurchaseListUseCase,
    private val deleteExpenseWithPurchaseUseCase: DeleteExpenseWithPurchaseUseCase
): ViewModel() {
    private val _state = MutableStateFlow(ExpenseState())
    val state = _state.asStateFlow()
    init {
        viewModelScope.launch {
            getExpenseWithPurchaseListUseCase(expenseId).collect { info ->
                _state.update {
                    it.copy(
                        expenseInfo = info.first,
                        purchaseList = info.second
                    )
                }
            }
        }
    }

    fun setDeletePurchaseInfo(purchaseInfo: PurchaseInfo) {
        _state.update { it.copy(targetPurchaseInfo = purchaseInfo) }
    }

    fun changeShowDeleteDialog(value: Boolean) {
        _state.update { it.copy(isShowDeleteDialog = value) }
    }

    fun deletePurchase() {
        if (state.value.targetPurchaseInfo == null) return
        viewModelScope.launch {
            deleteExpenseWithPurchaseUseCase(expenseId)
        }

        _state.update {
            it.copy(
                isShowDeleteDialog = false,
                targetPurchaseInfo = null
            )
        }
    }
}