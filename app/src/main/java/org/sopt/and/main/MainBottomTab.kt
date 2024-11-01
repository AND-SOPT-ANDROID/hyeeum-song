package org.sopt.and.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainBottomTab(
    val icon: ImageVector,
    val title: String,
    val route: MainTabRoute,
) {
    Home(
        icon = Icons.Outlined.Home,
        title = "홈",
        route = org.sopt.and.home.Home,
    ),
    Search(
        icon = Icons.Outlined.Search,
        title = "검색",
        route = org.sopt.and.search.Search,
    ),
    My(
        icon = Icons.Outlined.AccountCircle,
        title = "MY",
        route = org.sopt.and.my.My,
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainBottomTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (MainRoute) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}

