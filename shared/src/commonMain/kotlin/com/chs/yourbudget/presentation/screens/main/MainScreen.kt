package com.chs.yourbudget.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.yourbudget.presentation.common.ItemExpense
import androidx.compose.material3

.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onClickExpense: (Long) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val datePickerState = rememberDatePickerState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(state.expenseList) {
                ItemExpense(
                    expenseInfo = it,
                    onClick = onClickExpense,
                    onLonClick = {
                        viewModel.targetExpense(expenseInfo = it)
                        viewModel.changeDeleteDialogShow(true)
                    }
                )
            }
        }

        FloatingActionButton(
            onClick = { viewModel.changeDateDialogShow(true) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    bottom = 8.dp,
                    end = 8.dp
                ),
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null
            )
        }

        if (state.isShowDateDialog) {
            DatePickerDialog(
                onDismissRequest = { viewModel.changeDateDialogShow(false) },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.changeDateDialogShow(false)
                        if (datePickerState.selectedDateMillis == null) {
                            return@TextButton
                        }
                        viewModel.createExpense(datePickerState.selectedDateMillis!!)
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { viewModel.changeDateDialogShow(false) }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        if (state.isShowDeleteDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.changeDeleteDialogShow(false) },
                confirmButton = {
                    TextButton(onClick = { viewModel.deleteExpense() }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { viewModel.changeDeleteDialogShow(false) }) {
                        Text("No")
                    }
                },
                text = {
                    Text(text = "Are you sure delete Expense?")
                }
            )
        }
    }
}