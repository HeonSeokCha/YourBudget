package com.chs.yourbudget.presentation.screens.update_purchase

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.yourbudget.presentation.common.ItemPurchase
import com.chs.yourbudget.util.Constants
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
    val amountTextState = rememberTextFieldState("0")

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
                label = { Text("Title") }
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

//    if (state.isShowDeleteDialog) {
//        AlertDialog(
//            onDismissRequest = { viewModel.changeStateFromDeleteDialog(false) },
//            confirmButton = {
//                TextButton(onClick = { viewModel.deletePurchase() }) {
//                    Text("Yes")
//                }
//            },
//            dismissButton = {
//                TextButton(onClick = { viewModel.changeStateFromDeleteDialog(false) }) {
//                    Text("No")
//                }
//            },
//            text = {
//                Text(text = "Are you sure delete Expense?")
//            }
//        )
//    }
    var expanded by remember { mutableStateOf(false) }
    val textFieldState = rememberTextFieldState(Constants.USER_NAME_LIST.first())
    if (state.isShowDeleteDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.changeStateFromAddDialog(false) },
            confirmButton = {
                TextButton(onClick = { viewModel.deletePurchase() }) {
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
                            readOnly = true,
                            state = textFieldState,
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
                                        textFieldState.setTextAndPlaceCursorAtEnd(name)
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        state = amountTextState,
                        lineLimits = TextFieldLineLimits.SingleLine,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Amount") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        )
                    )
                }
            }
        )
    }
}