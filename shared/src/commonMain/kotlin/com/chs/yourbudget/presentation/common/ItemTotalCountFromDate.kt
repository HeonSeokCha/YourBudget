package com.chs.yourbudget.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chs.yourbudget.util.toCommaString
import com.chs.yourbudget.util.toMillis
import kotlinx.datetime.LocalDate


@Composable
fun ItemTotalCountFromDate(
    expenseSummaryInfo: Pair<LocalDate, Long>,
    onClick: (Long) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .clickable(
                onClick = { onClick(expenseSummaryInfo.first.toMillis()) }
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = expenseSummaryInfo.first.toString())
        Text(text = expenseSummaryInfo.second.toCommaString())
    }
}
