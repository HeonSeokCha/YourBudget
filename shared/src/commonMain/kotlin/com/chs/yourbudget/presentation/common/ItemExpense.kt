package com.chs.yourbudget.presentation.common

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chs.yourbudget.domain.model.ExpenseInfo

@Composable
fun ItemExpense(
    expenseInfo: ExpenseInfo,
    onClick: (Long) -> Unit
) {
    Row(
        modifier = Modifier.padding(24.dp)
            .clickable(onClick = { onClick(expenseInfo.expenseId) })
    ) {
        Text(text = expenseInfo.expenseDate.toString())
    }
}