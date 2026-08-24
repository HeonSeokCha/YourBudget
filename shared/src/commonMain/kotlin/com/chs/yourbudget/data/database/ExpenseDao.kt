package com.chs.yourbudget.data.database

import androidx.room3.Dao
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ExpenseDao : BaseDao<ExpenseInfoEntity> {
    @Query("SELECT * FROM expense_info as expense ORDER BY expenseDate DESC")
    abstract fun getAllExpenseList(): Flow<List<ExpenseInfoEntity>>

    @Query("""
        SELECT *
          FROM expense_info as expense
          LEFT JOIN purchases_info as purchase ON expense.idx = purchase.expenseIdx
         WHERE expense.idx = :expenseId
    """)
    abstract fun getExpenseInfo(expenseId: Long): Flow<Map<ExpenseInfoEntity, List<PurchaseInfoEntity>>>
}