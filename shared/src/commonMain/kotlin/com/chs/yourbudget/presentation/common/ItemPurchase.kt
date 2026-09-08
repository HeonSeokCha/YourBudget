package com.chs.yourbudget.presentation.common

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.yourbudget.domain.model.PurchaseInfo
import com.chs.yourbudget.util.toCommaString

@Composable
fun ItemPurchase(
    purchaseInfo: PurchaseInfo,
    onLonClick: (PurchaseInfo) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .combinedClickable(
                enabled = true,
                onClick = {},
                onLongClick = { onLonClick(purchaseInfo) }
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = purchaseInfo.userName, fontSize = 16.sp)
        Text(text = purchaseInfo.amount.toCommaString(), fontSize = 16.sp)
    }
}