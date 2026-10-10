package com.chs.yourbudget.domain.usecases

import com.chs.yourbudget.domain.BudgetRepository
import com.chs.yourbudget.domain.model.ExpenseInfo
import com.chs.yourbudget.domain.model.PurchaseInfo
import kotlinx.datetime.LocalDate
import org.koin.core.annotation.Single

@Single
class GetPurchasesFromUserUseCase(
    private val repository: BudgetRepository
) {
    suspend operator fun invoke(userName: String): LinkedHashMap<ExpenseInfo, List<PurchaseInfo>> {
        return repository.getPurchasesFromName(userName).sortedByDescending { it.first.expenseId }
            .toMap(LinkedHashMap())
    }
}