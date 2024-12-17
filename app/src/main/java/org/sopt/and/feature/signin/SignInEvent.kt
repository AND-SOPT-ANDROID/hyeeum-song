package org.sopt.and.feature.signin

sealed class SignInEvent {
    data class SetUsername(val username: String) : SignInEvent()
    data class SetPassword(val password: String) : SignInEvent()
}
