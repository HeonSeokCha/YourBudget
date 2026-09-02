package com.chs.yourbudget.domain

import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface BudgetRepository {
    suspend fun upsertPurchase(purchaseInfo: PurchaseInfo)
    suspend fun deletePurchase(purchaseInfo: PurchaseInfo)
    suspend fun insertExpense(expenseInfo: ExpenseInfo): Long
    suspend fun deleteExpense(expenseInfo: ExpenseInfo)
    suspend fun deleteExpenseWithPurchase(expenseId: Long)
    fun getAllExpense(): Flow<List<ExpenseInfo>>
    fun getExpenseListFromDate(targetDate: Long): Flow<List<Pair<ExpenseInfo, Long>>>
    suspend fun getExpenseWithPurchaseInfo(expenseId: Long): Pair<ExpenseInfo, List<PurchaseInfo>>
    suspend fun getTotalAmountByName(): Map<String, Long>
}