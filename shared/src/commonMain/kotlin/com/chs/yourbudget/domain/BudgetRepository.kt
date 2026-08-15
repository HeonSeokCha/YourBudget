package com.chs.yourbudget.domain

import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface BudgetRepository {
    suspend fun insertPurchase(purchaseInfo: PurchaseInfo)
    suspend fun deletePurchase(purchaseInfo: PurchaseInfo)
    suspend fun insertExpense(expenseInfo: ExpenseInfo)
    suspend fun deleteExpense(expenseInfo: ExpenseInfo)
    fun getAllExpense(): Flow<List<ExpenseInfo>>
    fun getDailyPurchaseList(targetDate: LocalDate): Flow<List<PurchaseInfo>>
    suspend fun getTotalAmount(userName: String): List<PurchaseInfo>
}