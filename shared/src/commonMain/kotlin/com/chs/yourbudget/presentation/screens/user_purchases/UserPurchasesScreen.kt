package com.chs.yourbudget.presentation.screens.user_purchases

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.yourbudget.util.toCommaString

@Composable
fun UserPurchasesScreen(
    viewModel: UserPurchasesViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        if (state.userName != null) {
            Text(
                text = state.userName!!,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(32.dp))
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            state.purchaseList.forEach { item ->
                stickyHeader {
                    Text(text = item.first.toString())

                    Spacer(modifier = Modifier.height(16.dp))
                }

                items(item.second) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = it.amount.toCommaString()
                        )

                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }
            }
        }
    }
}