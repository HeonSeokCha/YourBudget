package com.chs.yourbudget.presentation.screens.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.yourbudget.presentation.common.ItemExpense

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onClickExpense: (Long) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn {
        items(state.expenseList) {
            ItemExpense(
                expenseInfo = it,
                onClick = onClickExpense
            )
        }
    }
}