package org.sopt.and.signin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.and.main.MainTabRoute

fun NavController.navigateSignIn() {
    navigate(SignIn)
}

fun NavGraphBuilder.signInNavGraph(
    navigateUp: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    setSignInStateTrue: () -> Unit,
    signUpEmail: String,
    signUpPassword: String,
) {
    composable<SignIn> {
        SignInRoute(
            navigateUp = navigateUp,
            navigateToSignUp = navigateToSignUp,
            navigateToHome = navigateToHome,
            setSignInStateTrue = setSignInStateTrue,
            signUpEmail = signUpEmail,
            signUpPassword = signUpPassword,
        )
    }
}


@Serializable
data object SignIn : MainTabRoute
