package com.chs.yourbudget.domain.usecases

import com.chs.yourbudget.domain.BudgetRepository
import com.chs.yourbudget.domain.model.PurchaseInfo
import org.koin.core.annotation.Single

@Single
class DeletePurchaseUseCase(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(purchaseInfo: PurchaseInfo) {
        return repository.deletePurchase(purchaseInfo)
    }
}