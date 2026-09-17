package com.chs.yourbudget.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.chs.yourbudget.presentation.screens.bottom.BottomTopLevelBackStack
import org.jetbrains.compose.resources.stringResource
import yourbudget.shared.generated.resources.Res
import yourbudget.shared.generated.resources.app_name

@Composable
fun MainTopBar(
    backStack: BottomTopLevelBackStack,
    onBack: () -> Unit
) {
    when (backStack.backStack.last()) {
        BudgetScreens.ScreenMain, BudgetScreens.ScreenUserAmount -> {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.app_name),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }

        else -> {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}