package com.chs.yourbudget.presentation.screens.expense

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.yourbudget.presentation.common.ItemExpense
import com.chs.yourbudget.presentation.common.ItemPurchase

@Composable
fun ExpenseScreen(
    viewModel: ExpenseViewModel,
    onClickUpdateExpense: (Long) -> Unit
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
            items(state.expenseWithPurchaseList) {
                val expenseInfo = it.first
                ItemExpense(
                    expenseInfo = expenseInfo,
                    onClick = { onClickUpdateExpense(expenseInfo.expenseId) },
                    onLonClick = {
                        viewModel.setDeleteExpenseInfo(expenseInfo)
                        viewModel.changeShowDeleteDialog(true)
                    }
                )
            }
        }

        if (state.isShowDeleteDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.changeShowDeleteDialog(false) },
                confirmButton = {
                    TextButton(onClick = { viewModel.deleteExpenseInfo() }) {
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