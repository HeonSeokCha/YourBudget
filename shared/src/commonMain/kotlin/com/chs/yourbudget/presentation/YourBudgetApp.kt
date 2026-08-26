package com.chs.yourbudget.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.systemGesturesPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.chs.yourbudget.di.KoinModule
import org.koin.compose.KoinApplication
import org.koin.plugin.module.dsl.koinConfiguration

@Composable
fun YourBudgetApp() {
    KoinApplication(koinConfiguration<KoinModule>()) {
        val backStack: SnapshotStateList<BudgetScreens> = remember {
            mutableStateListOf(BudgetScreens.ScreenMain)
        }
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            topBar = {
                MainTopBar(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        ) {
            MainNavDisplay(
                modifier = Modifier.padding(it),
                backStack = backStack
            )
        }
    }
}