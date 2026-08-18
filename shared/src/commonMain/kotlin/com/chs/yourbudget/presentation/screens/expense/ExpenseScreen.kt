package com.chs.yourbudget.presentation.screens.expense

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.yourbudget.presentation.screens.purchase.PurchaseViewModel

@Composable
fun ExpenseScreen(viewModel: PurchaseViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn {

    }
}