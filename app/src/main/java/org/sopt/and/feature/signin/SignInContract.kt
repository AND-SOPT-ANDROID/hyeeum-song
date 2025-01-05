package org.sopt.and.feature.signin

class SignInContract {
    data class SignInState(
        val username: String = "",
        val password: String = "",
        var isPasswordVisible: Boolean = false,
    ) {
        val isButtonEnabled: Boolean = username.isNotEmpty() && password.isNotEmpty()
    }

    sealed class SignInEvent {
        data class SetUsername(val username: String) : SignInEvent()
        data class SetPassword(val password: String) : SignInEvent()
    }

    sealed class SignInSideEffect {
        data object NavigateToSignUp : SignInSideEffect()
        data object NavigateToHome : SignInSideEffect()
        data class ShowSnackBar(val snackBarMessage: String) : SignInSideEffect()
    }
}
