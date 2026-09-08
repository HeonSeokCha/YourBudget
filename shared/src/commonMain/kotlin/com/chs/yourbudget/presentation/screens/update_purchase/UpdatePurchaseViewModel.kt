package com.chs.yourbudget.presentation.screens.update_purchase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.model.PurchaseInfo
import com.chs.yourbudget.domain.usecases.DeleteExpenseWithPurchaseUseCase
import com.chs.yourbudget.domain.usecases.DeletePurchaseUseCase
import com.chs.yourbudget.domain.usecases.GetExpenseWithPurchasesUseCase
import com.chs.yourbudget.domain.usecases.InsertExpenseUseCase
import com.chs.yourbudget.domain.usecases.InsertPurchaseUseCase
import com.chs.yourbudget.util.toLocalDateTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel
import kotlin.time.Clock

@KoinViewModel
class UpdatePurchaseViewModel(
    @InjectedParam private val expenseId: Long,
    private val getExpenseWithPurchasesUseCase: GetExpenseWithPurchasesUseCase,
    private val insertPurchaseUseCase: InsertPurchaseUseCase,
    private val deletePurchaseUseCase: DeletePurchaseUseCase,
    private val insertExpenseUseCase: InsertExpenseUseCase,
    private val deleteExpenseWithPurchaseUseCase: DeleteExpenseWithPurchaseUseCase
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
                    expenseTitle = expenseInfo.title,
                    expenseTargetDate = expenseInfo.expenseDate
                )
            }
        }
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


    fun changeStateFromAddDialog(value: Boolean) {
        _state.update {
            it.copy(isShowAddDialog = value)
        }
    }

    fun insertPurchase(
        userName: String,
        amount: Long
    ) {
        if (_state.value.expenseInfo == null) return
        viewModelScope.launch {
            insertPurchaseUseCase(
                PurchaseInfo(
                    expenseId = _state.value.expenseInfo!!.expenseId,
                    userName = userName,
                    amount = amount,
                    createAt = Clock.System.now().toLocalDateTime()
                )
            )
        }
    }

    fun deletePurchase() {
        if (_state.value.targetPurchase == null) return
        viewModelScope.launch {
            deletePurchaseUseCase(_state.value.targetPurchase!!)
            _state.update { it.copy(isShowDeleteDialog = false, targetPurchase = null) }
        }
    }

    fun clickSave() {
        if (_state.value.expenseInfo == null) return
        viewModelScope.launch {
            _state.value.expenseInfo!!.copy(
                title = _state.value.expenseTitle!!,
                expenseDate = _state.value.expenseTargetDate!!
            ).run {
                insertExpenseUseCase(this)
            }
        }
    }

    fun deleteExpense() {
        if (_state.value.expenseInfo == null) return

        viewModelScope.launch {
            deleteExpenseWithPurchaseUseCase(_state.value.expenseInfo!!.expenseId)
        }
    }
}