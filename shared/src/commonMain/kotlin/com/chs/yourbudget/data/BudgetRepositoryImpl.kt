package com.chs.yourbudget.data

import com.chs.yourbudget.data.database.ExpenseDao
import com.chs.yourbudget.data.database.PurchaseDao
import com.chs.yourbudget.domain.BudgetRepository
import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.Single

@Single
class BudgetRepositoryImpl(
    private val purchaseDao: PurchaseDao,
    private val expenseDao: ExpenseDao
) : BudgetRepository {
    override suspend fun insertPurchase(purchaseInfo: PurchaseInfo) {
        purchaseDao.upsertEntity()
    }

    override suspend fun deletePurchase(purchaseInfo: PurchaseInfo) {
        TODO("Not yet implemented")
    }

    override suspend fun insertExpense(expenseInfo: ExpenseInfo) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteExpense(expenseInfo: ExpenseInfo) {
        TODO("Not yet implemented")
    }

    override fun getAllExpense(): Flow<List<ExpenseInfo>> {
        TODO("Not yet implemented")
    }

    override fun getDailyPurchaseList(targetDate: LocalDate): Flow<List<PurchaseInfo>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTotalAmount(userName: String): List<PurchaseInfo> {
        TODO("Not yet implemented")
    }
}