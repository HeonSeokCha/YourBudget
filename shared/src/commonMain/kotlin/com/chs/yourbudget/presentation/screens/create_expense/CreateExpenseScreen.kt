package com.chs.yourbudget.presentation.screens.create_expense

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateExpenseScreen(
    viewModel: CreateExpenseViewModel,
    clickSave: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val datePickerState = rememberDatePickerState()
    val titleTextState = rememberTextFieldState()

    LaunchedEffect(titleTextState.text) {
        if (titleTextState.text.isEmpty() || titleTextState.text.isBlank()) return@LaunchedEffect
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
        ) {
            OutlinedTextField(
                state = titleTextState,
                lineLimits = TextFieldLineLimits.SingleLine,
                label = { Text("Title") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = { viewModel.changeShowDateDialog(true) }
            ) {
                Text(text = state.expenseDate.toString())
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Purchases")

            LazyColumn {
                items(state.purchaseList) {

                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                viewModel.clickSave()
                clickSave()
            }
        ) {
            Text("Saved")
        }
    }

    if (state.isShowDateDialog) {
        DatePickerDialog(
            onDismissRequest = { viewModel.changeShowDateDialog(false) },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.changeShowDateDialog(false)
                    if (datePickerState.selectedDateMillis == null) return@TextButton
                    viewModel.updateExpenseDate(datePickerState.selectedDateMillis!!)
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.changeShowDateDialog(false) }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}