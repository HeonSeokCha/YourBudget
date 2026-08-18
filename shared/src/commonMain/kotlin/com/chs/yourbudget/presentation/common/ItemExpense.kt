package com.chs.yourbudget.presentation.common

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
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
fun ItemExpense() {
    var expanded by rememberSaveable { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Row(modifier = Modifier.padding(24.dp)) {
        Column(modifier = Modifier
            .weight(1f)
            .padding(bottom = extraPadding.coerceAtLeast(0.dp))
        ) {
            Text(text = "Hello, ")
            Text(text = "name")
        }
        ElevatedButton(
            onClick = { expanded = !expanded }
        ) {
            Text(if (expanded) "Show less" else "Show more")
        }
    }
}