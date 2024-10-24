package org.sopt.and.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.and.main.MainTabRoute

fun NavController.navigateSignUp() {
    navigate(SignUp)
}

fun NavGraphBuilder.signUpNavGraph(
    navigateUp: () -> Unit,
    navigateToSignIn: (String, String) -> Unit,
    saveUserInformation : (String,String) -> Unit
) {
    composable<SignUp> {
        SignUpRoute(
            navigateUp = navigateUp,
            navigateToSignIn = navigateToSignIn,
            saveUserInformation = saveUserInformation
        )
    }
}


@Serializable
data object SignUp : MainTabRoute
