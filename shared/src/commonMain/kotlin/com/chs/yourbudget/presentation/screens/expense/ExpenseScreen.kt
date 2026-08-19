package com.chs.yourbudget.presentation.screens.expense

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ExpenseScreen(
    viewModel: ExpenseViewModel,
    onClickCreatePurchase: (Long) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn {

    }
}