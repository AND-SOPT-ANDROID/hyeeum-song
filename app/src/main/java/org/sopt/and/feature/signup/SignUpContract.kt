package org.sopt.and.feature.signup

class SignUpContract {
    data class SignUpState(
        val username: String = "",
        val password: String = "",
        val hobby: String = "",
        var isPasswordVisible: Boolean = false,
    ) {
        val isButtonEnabled: Boolean = username.isNotEmpty() && password.isNotEmpty()
    }

    sealed class SignUpEvent {
        data class SetUsername(val username: String) : SignUpEvent()
        data class SetPassword(val password: String) : SignUpEvent()
        data class SetHobby(val hobby: String) : SignUpEvent()
    }

    sealed class SignUpSideEffect {
        data object NavigateToSignIn : SignUpSideEffect()
        data class ShowToast(val toastMessage: Int) : SignUpSideEffect()
    }
}
