package com.chs.yourbudget.domain.usecases

import com.chs.yourbudget.domain.BudgetRepository

class InsertExpenseUseCase(
    private val repository: BudgetRepository
) {
}