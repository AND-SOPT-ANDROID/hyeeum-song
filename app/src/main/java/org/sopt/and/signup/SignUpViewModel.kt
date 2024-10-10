package org.sopt.and.signup

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SignUpSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SignUpSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun setEmail(email: String) {
        _state.value = _state.value.copy(
            email = email
        )
        updateButtonState()
    }

    fun setPassword(password: String) {
        _state.value = _state.value.copy(
            password = password
        )
        updateButtonState()
    }

    private fun updateButtonState() {
        _state.value = _state.value.copy(
            isButtonEnabled = _state.value.email.isNotEmpty() && _state.value.password.isNotEmpty()
        )
    }

    fun isSignUpValid() {
        viewModelScope.launch {
            if (isEmailValid() && isPasswordValid()) {
                _sideEffect.emit(SignUpSideEffect.NavigateToSignIn)
            } else {
                val toastMessage = when {
                    !isEmailValid() -> "이메일을 다시 확인해주세요."
                    !isPasswordValid() -> "비밀번호는 대소문자, 숫자, 특수문자를 포함해야 합니다."
                    else -> "유효하지 않은 입력입니다."
                }
                _sideEffect.emit(SignUpSideEffect.ShowToast(toastMessage))
            }
        }
    }

    fun isEmailValid(): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS

        return pattern.matcher(_state.value.email).matches()
    }

    fun isPasswordValid(): Boolean {
        val password = _state.value.password

        if (password.length !in MIN_PASSWORD..MAX_PASSWORD) return false

        val validationCount = listOf(
            password.any { it.isUpperCase() },
            password.any { it.isLowerCase() },
            password.any { it.isDigit() },
            password.any { !it.isLetterOrDigit() }
        )

        return validationCount.count { it } >= 3
    }


    fun reversePasswordVisibility() {
        _state.value = _state.value.copy(
            isPasswordVisible = !_state.value.isPasswordVisible
        )
    }

    companion object {
        const val MIN_PASSWORD = 8
        const val MAX_PASSWORD = 20
    }
}
