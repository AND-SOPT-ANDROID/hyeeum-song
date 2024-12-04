package org.sopt.and.feature.signin

data class SignInState(
    val username: String = "",
    val password: String = "",
    var isPasswordVisible: Boolean = false,
) {
    val isButtonEnabled: Boolean = username.isNotEmpty() && password.isNotEmpty()
}
