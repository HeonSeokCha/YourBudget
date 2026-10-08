package com.chs.yourbudget.presentation.screens.user_amount

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.yourbudget.util.toCommaString

@Composable
fun UserAmountScreen(
    viewModel: UserAmountViewModel,
    onUserClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        state.userAmountList.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .clickable(
                        onClick = { onUserClick(it.first) }
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = it.first, fontSize = 16.sp)
                Text(text = it.second.toCommaString(), fontSize = 16.sp)
            }
        }
    }
}