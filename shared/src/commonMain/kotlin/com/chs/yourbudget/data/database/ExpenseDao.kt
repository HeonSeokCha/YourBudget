package com.chs.yourbudget.data.database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.MapColumn
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ExpenseDao : BaseDao<ExpenseInfoEntity> {
    @Insert
    abstract suspend fun insert(entity: ExpenseInfoEntity): Long

    @Query("SELECT * FROM expense_info as expense ORDER BY expenseDate DESC")
    abstract fun getAllExpenseList(): Flow<List<ExpenseInfoEntity>>

    @Query("""
        SELECT *
          FROM expense_info as expense
          LEFT JOIN purchases_info as purchase ON expense.idx = purchase.expenseIdx
         WHERE expense.idx = :expenseId
         LIMIT 1
    """)
    abstract suspend fun getExpenseInfoWithPurchase(expenseId: Long): Map<ExpenseInfoEntity, List<PurchaseInfoEntity>>

    @Query("""
        SELECT expense.*, IFNULL(SUM(purchase.amount), 0) as totalAmount
          FROM expense_info as expense
          LEFT JOIN purchases_info as purchase ON expense.idx = purchase.expenseIdx
         WHERE expense.expenseDate = :targetDate
    """)
    abstract fun getExpensesListFromDate(targetDate: Long): Flow<Map<ExpenseInfoEntity, @MapColumn("totalAmount") Long>>

    @Query("""
        SELECT expense.*, SUM(purchase.amount) as totalAmount
          FROM expense_info as expense
          LEFT JOIN purchases_info as purchase ON expense.idx = purchase.expenseIdx
    """)
    abstract fun getExpenseWithTotalAmount(): Flow<Map<ExpenseInfoEntity, @MapColumn("totalAmount") Long>>

    @Query("DELETE FROM expense_info WHERE idx = :expenseId")
    abstract suspend fun deleteFromExpenseId(expenseId: Long)
}