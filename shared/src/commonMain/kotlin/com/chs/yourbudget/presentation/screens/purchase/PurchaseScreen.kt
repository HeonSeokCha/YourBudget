package com.chs.yourbudget.presentation.screens.purchase

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.yourbudget.presentation.common.ItemPurchase

@Composable
fun PurchaseScreen(viewModel: PurchaseViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LazyColumn {
        item {
            Text(text = "This is Title..")
        }

        items(state.purchaseList) {
            ItemPurchase(it)
        }
    }
}