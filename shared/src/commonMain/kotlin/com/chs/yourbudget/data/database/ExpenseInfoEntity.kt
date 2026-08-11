package com.chs.yourbudget.data.database

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(
    tableName = "expense_info"
)
data class ExpenseInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val idx: Long = 0,
    val expenseDate: Long,
    val title: String,
    val memo: String?,
    val createAt: Long,
    val updateAt: Long?
)
