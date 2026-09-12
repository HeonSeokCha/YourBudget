package com.chs.yourbudget.presentation.screens.update_purchase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.yourbudget.presentation.common.ItemPurchase
import com.chs.yourbudget.util.Constants
import com.chs.yourbudget.util.MoneyOutputTransformation
import com.chs.yourbudget.util.digitsOnlyInputTransformation
import org.koin.core.logger.Logger
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePurchaseScreen(
    viewModel: UpdatePurchaseViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val titleTextState = rememberTextFieldState()
    var expanded by remember { mutableStateOf(false) }
    val amountTextState = rememberTextFieldState()
    val amount by remember {
        derivedStateOf { amountTextState.text.toString().toLongOrNull() ?: 0L }
    }
    val userNameState = rememberTextFieldState(Constants.USER_NAME_LIST.first())

    LaunchedEffect(state.expenseInfo?.title) {
        titleTextState.clearText()
        titleTextState.edit { append(state.expenseInfo?.title) }
    }

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
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Title") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = {},
                enabled = false
            ) {
                Text(text = state.expenseInfo?.expenseDate.toString())
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Purchases")

            Spacer(modifier = Modifier.height(32.dp))

            LazyColumn {
                items(state.purchaseList) {
                    ItemPurchase(
                        purchaseInfo = it,
                        onLonClick = {
                            viewModel.changeStateFromDeleteDialog(
                                value = true,
                                purchaseInfo = it
                            )
                        }
                    )
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(bottom = 8.dp, end = 8.dp)
                .align(Alignment.End),
            onClick = {
                viewModel.changeStateFromAddDialog(true)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                null
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f),
                onClick = {
                    viewModel.deleteExpense()
                    onBack()
                }
            ) {
                Text("Delete")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f),
                onClick = {
                    viewModel.clickSave()
                    onBack()
                }
            ) {
                Text("Saved")
            }

        }
    }


    if (state.isShowDeleteDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.changeStateFromDeleteDialog(false) },
            confirmButton = {
                TextButton(onClick = { viewModel.deletePurchase() }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.changeStateFromDeleteDialog(false) }) {
                    Text("No")
                }
            },
            text = {
                Text(text = "Are you sure delete Expense?")
            }
        )
    }

    if (state.isShowAddDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.changeStateFromAddDialog(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.insertPurchase(
                            userName = userNameState.text.toString(),
                            amount = amount
                        )
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