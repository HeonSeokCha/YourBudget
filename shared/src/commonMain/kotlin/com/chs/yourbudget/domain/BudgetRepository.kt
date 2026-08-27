package com.chs.yourbudget.domain

import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
    suspend fun insertPurchase(purchaseInfo: PurchaseInfo)
    suspend fun deletePurchase(purchaseInfo: PurchaseInfo)
    suspend fun insertExpense(expenseInfo: ExpenseInfo): Long
    suspend fun deleteExpense(expenseInfo: ExpenseInfo)
    fun getAllExpense(): Flow<List<ExpenseInfo>>
    fun getExpenseWithPurchaseInfo(expenseId: Long): Flow<Pair<ExpenseInfo, List<PurchaseInfo>>>
    suspend fun getTotalAmountByName(): Map<String, Long>
}