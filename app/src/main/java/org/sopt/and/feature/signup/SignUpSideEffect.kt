package org.sopt.and.feature.signup

sealed class SignUpSideEffect {
    data object NavigateToSignIn : SignUpSideEffect()
    data class ShowToast(val toastMessage: Int) : SignUpSideEffect()
}
