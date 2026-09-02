package com.chs.yourbudget.data

import com.chs.yourbudget.data.database.ExpenseDao
import com.chs.yourbudget.data.database.PurchaseDao
import com.chs.yourbudget.domain.BudgetRepository
import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo
import com.chs.yourbudget.util.toMillis
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.Single
import kotlin.math.exp

@Single
class BudgetRepositoryImpl(
    private val purchaseDao: PurchaseDao,
    private val expenseDao: ExpenseDao
) : BudgetRepository {
    override suspend fun upsertPurchase(purchaseInfo: PurchaseInfo) {
        purchaseDao.upsertEntity(purchaseInfo.toPurchaseInfoEntity())
    }

    override suspend fun deletePurchase(purchaseInfo: PurchaseInfo) {
        purchaseDao.deleteEntity(purchaseInfo.toPurchaseInfoEntity())
    }

    override suspend fun insertExpense(expenseInfo: ExpenseInfo): Long {
        return expenseDao.insert(expenseInfo.toExpenseInfoEntity())
    }

    override suspend fun deleteExpense(expenseInfo: ExpenseInfo) {
        expenseDao.deleteEntity(expenseInfo.toExpenseInfoEntity())
    }

    override suspend fun deleteExpenseWithPurchase(expenseId: Long) {
        expenseDao.deleteFromExpenseId(expenseId)
        purchaseDao.deleteFromExpenseId(expenseId)
    }

    override fun getAllExpense(): Flow<List<ExpenseInfo>> {
        return expenseDao.getAllExpenseList().map {
            it.map { it.toExpenseInfo() }
        }
    }

    override fun getExpenseListFromDate(targetDate: Long): Flow<List<Pair<ExpenseInfo, Long>>> {
        return expenseDao.getExpensesListFromDate(targetDate).map {
            it.map { it.key.toExpenseInfo() to it.value }
        }
    }

    override suspend fun getExpenseWithPurchaseInfo(expenseId: Long): Pair<ExpenseInfo, List<PurchaseInfo>> {
        return expenseDao.getExpenseInfoWithPurchase(expenseId).map {
            it.key.toExpenseInfo() to it.value.map { it.toPurchaseInfo() }
        }.single()
    }

    override suspend fun getTotalAmountByName(): Map<String, Long> {
        return purchaseDao.getTotalAmountByUserName()
    }
}