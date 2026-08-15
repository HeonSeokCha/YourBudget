package com.chs.yourbudget.domain.usecases

import com.chs.yourbudget.domain.BudgetRepository
import org.koin.core.annotation.Single

@Single
class DeleteExpenseUseCase(
    private val repository: BudgetRepository
){
}