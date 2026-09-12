package com.chs.yourbudget.presentation.screens.create_expense

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.yourbudget.util.Constants
import com.chs.yourbudget.util.MoneyOutputTransformation
import com.chs.yourbudget.util.digitsOnlyInputTransformation
import com.chs.yourbudget.util.toCommaString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateExpenseScreen(
    viewModel: CreateExpenseViewModel,
    clickSave: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val datePickerState = rememberDatePickerState()
    val titleTextState = rememberTextFieldState()

    var expanded by remember { mutableStateOf(false) }
    val amountTextState = rememberTextFieldState()
    val amount by remember {
        derivedStateOf { amountTextState.text.toString().toLongOrNull() ?: 0L }
    }
    val userNameState = rememberTextFieldState(Constants.USER_NAME_LIST.first())

    LaunchedEffect(titleTextState.text) {
        viewModel.updateExpenseTitle(titleTextState.text.toString())
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
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Title") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = it.first, fontSize = 16.sp)
                        Text(text = it.second.toCommaString(), fontSize = 16.sp)
                    }
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(bottom = 8.dp, end = 8.dp)
                .align(Alignment.End),
            onClick = { viewModel.changeStateFromAddDialog(true) }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                null
            )
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


    if (state.isShowAddDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.changeStateFromAddDialog(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.updatePurchaseList(userNameState.text.toString() to amount)
                    }
                ) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.changeStateFromAddDialog(false) }) {
                    Text("No")
                }
            },
            text = {
                Column {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            println(it)
                            expanded = it
                        }
                    ) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                            readOnly = true,
                            state = userNameState,
                            label = { Text("Label") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            Constants.USER_NAME_LIST.forEach { name ->
                                DropdownMenuItem(
                                    text = { Text(text = name) },
                                    onClick = {
                                        userNameState.setTextAndPlaceCursorAtEnd(name)
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        state = amountTextState,
                        label = { Text("Amount") },
                        placeholder = { Text("0") },
                        inputTransformation = digitsOnlyInputTransformation,
                        outputTransformation = MoneyOutputTransformation,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        lineLimits = TextFieldLineLimits.SingleLine,
                    )
                }
            }
        )
    }
}