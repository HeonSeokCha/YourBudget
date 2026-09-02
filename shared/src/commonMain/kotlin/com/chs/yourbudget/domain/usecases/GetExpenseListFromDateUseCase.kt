package com.chs.yourbudget.domain.usecases

import com.chs.yourbudget.domain.BudgetRepository
import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class GetExpenseListFromDateUseCase(
    private val repository: BudgetRepository
) {
    operator fun invoke(targetDate: Long): Flow<List<Pair<ExpenseInfo, Long>>> {
        return repository.getExpenseListFromDate(targetDate)
    }
}
