package com.chs.yourbudget.domain.usecases

import com.chs.yourbudget.domain.BudgetRepository
import org.koin.core.annotation.Single

@Single
class DeleteExpenseWithPurchaseUseCase(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(expenseId: Long) {
        return repository.deleteExpenseWithPurchase(expenseId)
    }
}