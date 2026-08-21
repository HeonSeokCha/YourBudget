package com.chs.yourbudget.domain.usecases

import com.chs.yourbudget.domain.BudgetRepository
import com.chs.yourbudget.domain.model.ExpenseInfo
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class GetAllExpenseUseCase(
    private val repository: BudgetRepository
) {
    operator fun invoke(): Flow<List<ExpenseInfo>> {
        return repository.getAllExpense()
    }
}