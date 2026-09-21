package com.chs.yourbudget.data

import com.chs.yourbudget.data.database.ExpenseDao
import com.chs.yourbudget.data.database.PurchaseDao
import com.chs.yourbudget.domain.BudgetRepository
import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo
import com.chs.yourbudget.util.toLocalDate
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
    override suspend fun upsertPurchase(vararg purchaseInfo: PurchaseInfo) {
        purchaseDao.upsertEntity(*purchaseInfo.map { it.toPurchaseInfoEntity()}.toTypedArray())
    }

    override suspend fun deletePurchase(purchaseInfo: PurchaseInfo) {
        purchaseDao.deleteEntity(purchaseInfo.toPurchaseInfoEntity())
    }

    override suspend fun insertExpense(expenseInfo: ExpenseInfo): Long {
        return expenseDao.upsertEntity(expenseInfo.toExpenseInfoEntity())
    }

    override suspend fun deleteExpense(expenseInfo: ExpenseInfo) {
        expenseDao.deleteEntity(expenseInfo.toExpenseInfoEntity())
    }

    override suspend fun deleteExpenseWithPurchase(expenseId: Long) {
        expenseDao.deleteFromExpenseId(expenseId)
        purchaseDao.deleteFromExpenseId(expenseId)
    }

    override fun getAllExpense(): Flow<List<Pair<LocalDate, Long>>> {
        return expenseDao.getAllExpenseList().map {
            it.map { it.key.toLocalDate() to it.value }
        }
    }

    override fun getExpenseListFromDate(targetDate: Long): Flow<List<Pair<ExpenseInfo, Long>>> {
        return expenseDao.getExpensesListFromDate(targetDate).map {
            it.map { it.key.toExpenseInfo() to it.value }
        }
    }

    override fun getExpenseWithPurchaseInfo(expenseId: Long): Flow<Map<ExpenseInfo, List<PurchaseInfo>>> {
        return expenseDao.getExpenseInfoWithPurchase(expenseId).map {
            it.map { it.key.toExpenseInfo() to it.value.map { it.toPurchaseInfo() } }.toMap()
        }
    }

    override suspend fun getTotalAmountByName(): List<Pair<String, Long>> {
        return purchaseDao.getTotalAmountByUserName().toList()
    }

    override suspend fun getPurchasesFromName(userName: String): List<PurchaseInfo> {
        return purchaseDao.getPurchasesFromUserName(userName).map { it.toPurchaseInfo() }
    }
}