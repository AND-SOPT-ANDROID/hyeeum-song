package org.sopt.and.signup

data class SignUpState(
    val email: String = "",
    val password: String = "",
    var isPasswordVisible: Boolean = false,
    val isButtonEnabled: Boolean = false
)
