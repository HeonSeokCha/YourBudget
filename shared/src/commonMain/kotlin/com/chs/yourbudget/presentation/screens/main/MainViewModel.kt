package com.chs.yourbudget.presentation.screens.main

import androidx.lifecycle.ViewModel
import com.chs.yourbudget.domain.usecases.GetAllExpenseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val getAllExpenseUseCase: GetAllExpenseUseCase
): ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()
}