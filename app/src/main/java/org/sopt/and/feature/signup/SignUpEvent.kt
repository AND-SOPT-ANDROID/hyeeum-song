package org.sopt.and.feature.signup

sealed class SignUpEvent {
    data class SetUsername(val username: String) : SignUpEvent()
    data class SetPassword(val password: String) : SignUpEvent()
    data class SetHobby(val hobby: String) : SignUpEvent()
}
