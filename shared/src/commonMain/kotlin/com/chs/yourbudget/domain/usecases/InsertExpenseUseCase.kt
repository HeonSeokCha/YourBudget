package com.chs.yourbudget.domain.usecases

import com.chs.yourbudget.domain.BudgetRepository
import com.chs.yourbudget.domain.model.ExpenseInfo
import org.koin.core.annotation.Single

@Single
class InsertExpenseUseCase(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(expenseInfo: ExpenseInfo) {
        return repository.insertExpense(expenseInfo)
    }
}