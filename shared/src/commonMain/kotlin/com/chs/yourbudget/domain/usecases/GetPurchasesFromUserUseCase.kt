package com.chs.yourbudget.domain.usecases

import com.chs.yourbudget.domain.BudgetRepository
import com.chs.yourbudget.domain.model.PurchaseInfo
import org.koin.core.annotation.Single

@Single
class GetPurchasesFromUserUseCase(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(userName: String): List<PurchaseInfo> {
        return repository.getPurchasesFromName(userName)
    }
}