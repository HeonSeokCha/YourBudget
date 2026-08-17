package com.chs.yourbudget.data.database

import androidx.room3.Dao
import androidx.room3.MapColumn
import androidx.room3.Query

@Dao
abstract class PurchaseDao : BaseDao<PurchaseInfoEntity> {

    @Query(
        """
         SELECT expense.expenseDate, purchase.userName, SUM(purchase.amount) as totalMount
          FROM expense_info as expense
          LEFT JOIN purchases_info as purchase ON expense.idx = purchase.expenseIdx
          GROUP BY expense.expenseDate, purchase.userName
          ORDER BY expense.expenseDate
    """
    )
    abstract suspend fun getDailyTotalAmountFromUser(): Map<@MapColumn("expenseDate") Long, Map<@MapColumn(
        "userName"
    ) String, @MapColumn("totalMount") Long>>

    @Query(
        """
         SELECT userName, SUM(amount) as totalAmount
           FROM purchases_info
           GROUP BY userName
    """
    )
    abstract suspend fun getTotalAmountFromUser(): Map<@MapColumn("userName") String, @MapColumn("totalAmount") Long>

    @Query("""
        SELECT userName, SUM(amount) as totalAmount
          FROM purchase_info
          GROUP BY userName
    """)
    abstract suspend fun getTotalAmountByUserName(): Map<@MapColumn("userName") String, @MapColumn("totalAmount") Long>
}