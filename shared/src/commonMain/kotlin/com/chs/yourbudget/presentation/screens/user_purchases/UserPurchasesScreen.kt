package com.chs.yourbudget.presentation.screens.user_purchases

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.yourbudget.util.toCommaString

@Composable
fun UserPurchasesScreen(
    viewModel: UserPurchasesViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        state.purchaseList.forEach {
            Column {
                Text(text = it.first.toString())
                it.second.forEach {
                    it.amount.toCommaString()
                }
            }
        }
    }
}