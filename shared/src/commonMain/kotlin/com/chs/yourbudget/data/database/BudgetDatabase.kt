package com.chs.yourbudget.data.database

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase

@Database(
    entities = [
        ExpenseInfoEntity::class,
        PurchaseInfoEntity::class
    ],
    version = 1,
    exportSchema = false
)
@ConstructedBy(BudgetDatabaseConstructor::class)
abstract class BudgetDatabase : RoomDatabase() {
    abstract val expenseDao: ExpenseDao
    abstract val purchaseDao: PurchaseDao
}