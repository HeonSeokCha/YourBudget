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
    """)
    abstract fun getExpenseInfo(expenseId: Long): Flow<Map<ExpenseInfoEntity, List<PurchaseInfoEntity>>>


    @Query("""
        SELECT expense.*, SUM(purchase.amount) as totalAmount
          FROM expense_info as expense
          LEFT JOIN purchases_info as purchase ON expense.idx = purchase.expenseIdx
    """)
    abstract fun getExpenseWithTotalAmount(): Flow<Map<ExpenseInfoEntity, @MapColumn("totalAmount") Long>>
}