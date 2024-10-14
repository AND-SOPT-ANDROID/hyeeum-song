package org.sopt.and.signup

sealed class SignUpSideEffect {
    data object NavigateToSignIn : SignUpSideEffect()
    data class ShowToast(val toastMessage: String) : SignUpSideEffect()
}
