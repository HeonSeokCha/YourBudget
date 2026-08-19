package com.chs.yourbudget.presentation.screens.purchase_create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PurchaseCreateScreen(
    viewModel: PurchaseViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

}