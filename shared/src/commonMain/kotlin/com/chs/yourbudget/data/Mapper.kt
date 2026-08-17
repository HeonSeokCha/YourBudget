package com.chs.yourbudget.data

import com.chs.yourbudget.data.database.ExpenseInfoEntity
import com.chs.yourbudget.data.database.PurchaseInfoEntity
import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo
import com.chs.yourbudget.util.toLocalDate
import com.chs.yourbudget.util.toLocalDateTime
import com.chs.yourbudget.util.toMillis

fun ExpenseInfo.toExpenseInfoEntity(): ExpenseInfoEntity {
    return ExpenseInfoEntity(
        idx = this.expenseId,
        expenseDate = this.expenseDate.toMillis(),
        createAt = this.createTime.toMillis(),
        updateAt = this.updateTime?.toMillis()
    )
}

fun ExpenseInfoEntity.toExpenseInfo(): ExpenseInfo {
    return ExpenseInfo(
        expenseId = this.idx,
        expenseDate = this.expenseDate.toLocalDate(),
        createTime = this.createAt.toLocalDateTime(),
        updateTime = this.updateAt?.toLocalDateTime()
    )
}

fun PurchaseInfo.toPurchaseInfoEntity(): PurchaseInfoEntity {
    return PurchaseInfoEntity(
        idx = this.purchaseId,
        expenseIdx = this.expenseId,
        userName = this.userName,
        amount = this.amount,
        createAt = this.createAt.toMillis()
    )
}

fun PurchaseInfoEntity.toPurchaseInfo(): PurchaseInfo {
    return PurchaseInfo(
        purchaseId = this.idx,
        expenseId = this.expenseIdx,
        userName = this.userName,
        amount = this.amount,
        createAt = this.createAt.toLocalDateTime()
    )
}
