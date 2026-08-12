package com.chs.yourbudget.data.database

import androidx.room3.Dao
import androidx.room3.Query

@Dao
abstract class ExpenseDao : BaseDao<ExpenseInfoEntity> {
    @Query("""
        SELECT *
          FROM expense_info as expense
          LEFT JOIN purchases_info as purchase ON expense.idx = purchase.expenseIdx
         WHERE expenseDate = :targetDate
    """)
    abstract suspend fun getExpenseFromDate(targetDate: Long): Map<PurchaseInfoEntity, List<ExpenseInfoEntity>>
}