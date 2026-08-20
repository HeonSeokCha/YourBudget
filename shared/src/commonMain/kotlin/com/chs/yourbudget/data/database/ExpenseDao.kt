package com.chs.yourbudget.data.database

import androidx.room3.Dao
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ExpenseDao : BaseDao<ExpenseInfoEntity> {
    @Query("SELECT * FROM expense_info as expense")
    abstract fun getAllExpenseList(): Flow<List<ExpenseInfoEntity>>

    @Query("""
        SELECT *
          FROM expense_info as expense
          LEFT JOIN purchases_info as purchase ON expense.idx = purchase.expenseIdx
         WHERE expenseId = :expenseId
    """)
    abstract suspend fun getExpenseInfo(expenseId: Long): Map<ExpenseInfoEntity, List<PurchaseInfoEntity>>
}