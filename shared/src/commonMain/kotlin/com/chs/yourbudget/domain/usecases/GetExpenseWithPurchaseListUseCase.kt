package com.chs.yourbudget.domain.usecases

import com.chs.yourbudget.domain.BudgetRepository
import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo
import org.koin.core.annotation.Single

@Single
class GetExpenseWithPurchaseListUseCase(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(expenseId: Long): Pair<ExpenseInfo, List<PurchaseInfo>> {
        return repository.getExpenseWithPurchaseInfo(expenseId)
    }
}