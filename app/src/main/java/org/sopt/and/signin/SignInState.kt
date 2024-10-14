package org.sopt.and.signin

data class SignInState (
    val email: String = "",
    val password: String = "",
    var isPasswordVisible: Boolean = false,
    val isButtonEnabled : Boolean = false
)
