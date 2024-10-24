package org.sopt.and.my

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.and.main.MainTabRoute
import org.sopt.and.sharedpreference.User

fun NavController.navigateMy(
    navOptions: NavOptions
) {
    navigate(My, navOptions)
}

fun NavGraphBuilder.myNavGraph(
    paddingValues: PaddingValues,
    user: User,
) {
    composable<My> {
        MyRoute(
            paddingValues = paddingValues,
            email = user.getEmail().toString()
        )
    }
}

@Serializable
data object My : MainTabRoute
