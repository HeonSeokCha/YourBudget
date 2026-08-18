package com.chs.yourbudget.presentation.screens.purchase

import androidx.lifecycle.ViewModel
import com.chs.yourbudget.domain.usecases.GetPurchaseFromDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class PurchaseViewModel(
    @InjectedParam private val targetDate: LocalDate,
    private val getPurchaseFromDate: GetPurchaseFromDate
) : ViewModel() {
    private val _state = MutableStateFlow(PurchaseState())
    val state = _state.asStateFlow()

    init {

    }
}