package com.chs.yourbudget.data.database

import androidx.room3.RoomDatabaseConstructor

expect object BudgetDatabaseConstructor : RoomDatabaseConstructor<BudgetDatabase> {
    override fun initialize(): BudgetDatabase
}
