package com.chs.yourbudget.presentation.screens

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import com.chs.yourbudget.presentation.BottomNavigation
import com.chs.yourbudget.presentation.BudgetScreens
import kotlin.collections.removeLast

@Composable
fun BottomBar(backStack: SnapshotStateList<BudgetScreens>) {
    if (BottomNavigation.entries.any { it.route == backStack.last() }) {
        NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {
            BottomNavigation.entries.forEach { navItem ->
                NavigationBarItem(
                    selected = backStack.last() == navItem.route,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.White.copy(0.4f),
                        unselectedTextColor = Color.White.copy(0.4f),
                        indicatorColor = MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        backStack.clear()
                        backStack.add(navItem.route)
                    },
                    icon = { Icon(imageVector = navItem.icon, contentDescription = null) },
                    label = { Text(text = navItem.label) }
                )
            }
        }
    }
}
