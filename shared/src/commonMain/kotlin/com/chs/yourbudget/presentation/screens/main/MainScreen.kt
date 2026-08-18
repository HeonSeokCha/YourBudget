package com.chs.yourbudget.presentation.screens.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.yourbudget.presentation.common.ItemExpense

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn {
        items(10) {
            ItemExpense()
        }
    }
}