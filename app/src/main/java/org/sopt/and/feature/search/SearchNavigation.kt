package org.sopt.and.feature.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.and.feature.main.MainTabRoute

fun NavController.navigateSearch(
    navOptions: NavOptions
) {
    navigate(org.sopt.and.feature.search.Search, navOptions)
}

fun NavGraphBuilder.searchNavGraph(
    paddingValues: PaddingValues,
) {
    composable<org.sopt.and.feature.search.Search> {
        org.sopt.and.feature.search.SearchRoute(paddingValues = paddingValues)
    }
}

@Serializable
data object Search : MainTabRoute
