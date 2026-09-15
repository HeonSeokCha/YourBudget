package com.chs.yourbudget.presentation.screens.user_amount

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class UserAmountViewModel(

) : ViewModel() {
    private val _state = MutableStateFlow(UserAmountState())
    val state = _state.asStateFlow()
}