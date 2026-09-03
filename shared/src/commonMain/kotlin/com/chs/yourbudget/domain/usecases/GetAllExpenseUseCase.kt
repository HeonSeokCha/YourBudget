package com.chs.yourbudget.domain.usecases

import com.chs.yourbudget.domain.BudgetRepository
import com.chs.yourbudget.domain.model.ExpenseInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.Single

@Single
class GetAllExpenseUseCase(
    private val repository: BudgetRepository
) {
    operator fun invoke(): Flow<List<Pair<LocalDate, Long>>> {
        return repository.getAllExpense()
    }
}