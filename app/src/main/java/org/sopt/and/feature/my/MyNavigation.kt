package org.sopt.and.feature.my

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.and.core.navigation.MainTabRoute

fun NavController.navigateMy(
    navOptions: NavOptions
) {
    navigate(My, navOptions)
}

fun NavGraphBuilder.myNavGraph(
    paddingValues: PaddingValues,
) {
    composable<My> {
        MyRoute(
            paddingValues = paddingValues,
        )
    }
}

@Serializable
data object My : MainTabRoute
