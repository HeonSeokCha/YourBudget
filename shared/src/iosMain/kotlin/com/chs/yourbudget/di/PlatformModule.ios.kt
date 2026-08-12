package com.chs.yourbudget.di

import androidx.room3.Room
import com.chs.yourbudget.data.database.BudgetDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@Module
actual class PlatformModule {
    @OptIn(ExperimentalForeignApi::class)
    @Single
    fun provideDatabase(): BudgetDatabase {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        ).run { requireNotNull(this?.path())}

        val dbFile = "$documentDirectory/your_splash.db"
        return Room.databaseBuilder<BudgetDatabase>(
            name = dbFile
        )
            .setDriver(androidx.sqlite.driver.bundled.BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

}