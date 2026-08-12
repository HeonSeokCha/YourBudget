package com.chs.yourbudget.data.database

import androidx.room3.Delete
import androidx.room3.Upsert


interface BaseDao<T> {
    @Upsert
    suspend fun upsertEntity(vararg entity: T)

    @Delete
    suspend fun deleteEntity(vararg entity: T)
}