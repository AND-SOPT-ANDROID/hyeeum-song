package org.sopt.and.feature.signup

data class SignUpState(
    val username: String = "",
    val password: String = "",
    val hobby: String = "",
    var isPasswordVisible: Boolean = false,
) {
    val isButtonEnabled: Boolean = username.isNotEmpty() && password.isNotEmpty()
}
