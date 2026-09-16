package com.chs.yourbudget.domain.usecases

import com.chs.yourbudget.domain.BudgetRepository
import org.koin.core.annotation.Single

@Single
class GetTotalAmountUserUseCase(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(): List<Pair<String, Long>> {
        return repository.getTotalAmountByName()
    }
}