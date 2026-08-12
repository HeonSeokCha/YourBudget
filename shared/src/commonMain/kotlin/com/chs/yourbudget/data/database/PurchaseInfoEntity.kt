package com.chs.yourbudget.data.database

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "purchases_info",
    foreignKeys = [
        ForeignKey(
            entity = ExpenseInfoEntity::class,
            parentColumns = ["idx"],
            childColumns = ["expenseIdx"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("expenseIdx")]
)
data class PurchaseInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val idx: Long = 0,
    val expenseIdx: Long,
    val title: String,
    val amount: Long,
    val userName: String,
    val createAt: Long,
    val updateAt: Long?
)
