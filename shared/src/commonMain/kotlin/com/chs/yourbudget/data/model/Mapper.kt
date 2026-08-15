package com.chs.yourbudget.data.model

import com.chs.yourbudget.data.database.ExpenseInfoEntity
import com.chs.yourbudget.data.database.PurchaseInfoEntity
import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo

fun ExpenseInfo.toExpenseInfoEntity(): ExpenseInfoEntity {
}

fun ExpenseInfoEntity.toExpenseInfo(): ExpenseInfo {

}

fun PurchaseInfo.toPurchaseInfoEntity(): PurchaseInfoEntity {

}

fun PurchaseInfoEntity.toPurchaseInfo(): PurchaseInfo {

}
