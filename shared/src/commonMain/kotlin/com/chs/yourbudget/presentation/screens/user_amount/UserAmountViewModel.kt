package com.chs.yourbudget.presentation.screens.user_amount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chs.yourbudget.domain.usecases.GetTotalAmountUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class UserAmountViewModel(
    private val getTotalAmountUserUseCase: GetTotalAmountUserUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UserAmountState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(userAmountList = getTotalAmountUserUseCase())
            }
        }
    }
}