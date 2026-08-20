package com.chs.yourbudget.domain

import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
    suspend fun insertPurchase(purchaseInfo: PurchaseInfo)
    suspend fun deletePurchase(purchaseInfo: PurchaseInfo)
    suspend fun insertExpense(expenseInfo: ExpenseInfo)
    suspend fun deleteExpense(expenseInfo: ExpenseInfo)
    fun getAllExpense(): Flow<List<ExpenseInfo>>
    suspend fun getExpenseWithPurchaseInfo(expenseId: Long): Pair<ExpenseInfo, List<PurchaseInfo>>
    suspend fun getTotalAmountByName(): Map<String, Long>
}