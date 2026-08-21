package com.chs.yourbudget.domain.usecases

import com.chs.yourbudget.domain.BudgetRepository
import com.chs.yourbudget.domain.model.ExpenseInfo

class InsertExpenseUseCase(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(expenseInfo: ExpenseInfo) {
        return repository.insertExpense(expenseInfo)
    }
}