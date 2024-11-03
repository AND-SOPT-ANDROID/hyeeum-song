package org.sopt.and.signup

import android.util.Patterns
import androidx.compose.ui.res.stringResource
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
import org.sopt.and.R
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
    }

    fun setPassword(password: String) {
        _state.value = _state.value.copy(
            password = password
        )
    }

    fun isSignUpValid() {
        viewModelScope.launch {
            if (isEmailValid() && isPasswordValid()) {
                _sideEffect.emit(SignUpSideEffect.NavigateToSignIn)
            } else {
                val toastMessage = when {
                    !isEmailValid() -> R.string.check_email
                    !isPasswordValid() -> R.string.password_condition
                    else -> R.string.not_valid_input
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

        val validationCount = password.run {
            listOf(
                any { it.isUpperCase() },
                any { it.isLowerCase() },
                any { it.isDigit() },
                any { !it.isLetterOrDigit() }
            )
        }

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
