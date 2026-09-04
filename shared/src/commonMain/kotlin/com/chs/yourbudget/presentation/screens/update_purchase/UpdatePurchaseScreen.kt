package com.chs.yourbudget.presentation.screens.update_purchase

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
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.yourbudget.presentation.common.ItemPurchase
import com.chs.yourbudget.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePurchaseScreen(
    viewModel: UpdatePurchaseViewModel,
    clickSave: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val titleTextState = rememberTextFieldState(state.expenseInfo?.title ?: "")

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
                label = { Text(state.expenseInfo?.title ?: "") },
                readOnly = true,
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

            LazyColumn {
                items(state.purchaseList) {
                    ItemPurchase(
                        purchaseInfo = it,
                        onLonClick = { }
                    )
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
}