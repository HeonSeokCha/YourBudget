package com.chs.yourbudget.presentation.screens.expense

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.yourbudget.presentation.common.ItemPurchase

@Composable
fun ExpenseScreen(
    viewModel: ExpenseViewModel,
    onClickCreatePurchase: (Long) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (state.expenseInfo != null) {
                item {
                    Text(state.expenseInfo!!.expenseDate.toString())
                }
            }

            items(state.purchaseList) {
                ItemPurchase(purchaseInfo = it) {
                    viewModel.setDeletePurchaseInfo(it)
                    viewModel.changeShowDeleteDialog(true)
                }
            }
        }

        if (state.isShowDeleteDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.changeShowDeleteDialog(false) },
                confirmButton = {
                    TextButton(onClick = { viewModel.deletePurchase() }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { viewModel.changeShowDeleteDialog(false) }) {
                        Text("No")
                    }
                },
                text = {
                    Text(text = "Are you sure delete Purchase?")
                }
            )
        }
    }
}