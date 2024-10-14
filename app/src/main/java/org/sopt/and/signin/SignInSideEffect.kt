package org.sopt.and.signin

sealed class SignInSideEffect {
    data object NavigateToSignUp : SignInSideEffect()
    data object NavigateToMy : SignInSideEffect()
    data class ShowSnackBar(val snackBarMessage: String) : SignInSideEffect()
}
