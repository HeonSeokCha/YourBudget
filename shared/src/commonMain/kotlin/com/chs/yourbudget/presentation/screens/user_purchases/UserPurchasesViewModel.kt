package com.chs.yourbudget.presentation.screens.user_purchases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.usecases.GetPurchasesFromUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class UserPurchasesViewModel(
    @InjectedParam private val userName: String,
    private val getPurchasesFromUserUseCase: GetPurchasesFromUserUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UserPurchasesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    userName = userName,
                    purchaseList = getPurchasesFromUserUseCase(userName)
                )
            }
        }
    }

}