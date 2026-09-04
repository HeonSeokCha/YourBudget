package com.chs.yourbudget.data.database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.MapColumn
import androidx.room3.Query
import com.chs.yourbudget.domain.model.ExpenseInfo
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ExpenseDao : BaseDao<ExpenseInfoEntity> {

    @Query("""
        SELECT expense.expenseDate, IFNULL(SUM(purchase.amount),0) as totalAmount
          FROM expense_info as expense
          LEFT JOIN purchases_info as purchase ON expense.idx = purchase.expenseIdx
          GROUP BY expense.expenseDate
    """)
    abstract fun getAllExpenseList(): Flow<Map< @MapColumn("expenseDate") Long, @MapColumn("totalAmount") Long>>

    @Query("""
        SELECT *
          FROM expense_info as expense
          LEFT JOIN purchases_info as purchase ON expense.idx = purchase.expenseIdx
         WHERE expense.idx = :expenseId
    """)
    abstract suspend fun getExpenseInfoWithPurchase(expenseId: Long): Map<ExpenseInfoEntity, List<PurchaseInfoEntity>>

    @Query("""

        SELECT expense.*, IFNULL(SUM(purchase.amount), 0) as totalAmount
          FROM expense_info as expense
          LEFT JOIN purchases_info as purchase ON expense.idx = purchase.expenseIdx
         WHERE expense.expenseDate = :targetDate 
         GROUP BY expense.idx
         order by expense.idx 
    """)
    abstract fun getExpensesListFromDate(targetDate: Long): Flow<Map<ExpenseInfoEntity, @MapColumn("totalAmount") Long>>

    @Query("DELETE FROM expense_info WHERE idx = :expenseId")
    abstract suspend fun deleteFromExpenseId(expenseId: Long)
}