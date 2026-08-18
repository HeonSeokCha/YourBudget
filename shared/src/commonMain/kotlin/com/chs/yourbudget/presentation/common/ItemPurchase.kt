package com.chs.yourbudget.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chs.yourbudget.domain.model.PurchaseInfo
import com.chs.yourbudget.util.toCommaString

@Composable
fun ItemPurchase(purchaseInfo: PurchaseInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = purchaseInfo.userName)
        Text(text = purchaseInfo.amount.toCommaString())
    }
}