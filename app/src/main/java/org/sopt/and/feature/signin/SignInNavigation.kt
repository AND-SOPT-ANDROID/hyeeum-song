package org.sopt.and.feature.signin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.and.core.navigation.MainTabRoute

fun NavController.navigateSignIn() {
    navigate(SignIn)
}

fun NavGraphBuilder.signInNavGraph(
    navigateUp: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
) {
    composable<SignIn> {
        SignInRoute(
            navigateUp = navigateUp,
            navigateToSignUp = navigateToSignUp,
            navigateToHome = navigateToHome,
        )
    }
}


@Serializable
data object SignIn : MainTabRoute
