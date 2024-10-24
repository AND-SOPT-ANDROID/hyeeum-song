package org.sopt.and.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.and.main.MainTabRoute

fun NavController.navigateHome(
    navOptions: NavOptions
) {
    navigate(Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues,
) {
    composable<Home> {
        HomeRoute(paddingValues = paddingValues)
    }
}

@Serializable
data object Home : MainTabRoute
