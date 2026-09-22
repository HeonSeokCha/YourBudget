package com.chs.yourbudget.data.database

import androidx.room3.Dao
import androidx.room3.MapColumn
import androidx.room3.Query

@Dao
abstract class PurchaseDao : BaseDao<PurchaseInfoEntity> {
    @Query("""
        SELECT userName, SUM(amount) as totalAmount
          FROM purchases_info
          GROUP BY userName
          ORDER BY totalAmount DESC
    """)
    abstract suspend fun getTotalAmountByUserName(): Map<@MapColumn("userName") String, @MapColumn("totalAmount") Long>

    @Query("DELETE FROM purchases_info WHERE expenseIdx = :expenseId")
    abstract suspend fun deleteFromExpenseId(expenseId: Long)

    @Query("""
        SELECT a.expenseDate, b.*
          FROM expense_info as A
          LEFT JOIN purchases_info as b ON a.idx = b.expenseIdx
         WHERE b.userName = :userName
    """)
    abstract suspend fun getPurchasesFromUserName(userName: String): Map<@MapColumn("expenseDate") Long, List<PurchaseInfoEntity>>
}