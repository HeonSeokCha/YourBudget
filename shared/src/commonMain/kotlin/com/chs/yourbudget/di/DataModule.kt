package com.chs.yourbudget.di

import com.chs.yourbudget.data.database.BudgetDatabase
import com.chs.yourbudget.data.database.ExpenseDao
import com.chs.yourbudget.data.database.PurchaseDao
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module(includes = [PlatformModule::class])
@ComponentScan("com.chs.yourbudget.data")
class DataModule {
    @Single
    fun provideExpenseDao(db: BudgetDatabase): ExpenseDao = db.expenseDao

    @Single
    fun providePurchaseDao(db: BudgetDatabase): PurchaseDao = db.purchaseDao
}
