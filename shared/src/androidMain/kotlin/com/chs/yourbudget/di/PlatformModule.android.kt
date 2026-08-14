package com.chs.yourbudget.di

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.chs.yourbudget.data.database.BudgetDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
actual class PlatformModule {
    @Single
    fun provideDatabase(context: Context): BudgetDatabase {
        val dbFile = context.getDatabasePath("your_budget.db")
        return Room.databaseBuilder<BudgetDatabase>(context, dbFile.absolutePath)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}