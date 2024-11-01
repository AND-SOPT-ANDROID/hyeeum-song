package org.sopt.and.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.and.main.MainTabRoute

fun NavController.navigateSearch(
    navOptions: NavOptions
) {
    navigate(Search, navOptions)
}

fun NavGraphBuilder.searchNavGraph(
    paddingValues: PaddingValues,
) {
    composable<Search> {
        SearchRoute(paddingValues = paddingValues)
    }
}

@Serializable
data object Search : MainTabRoute
