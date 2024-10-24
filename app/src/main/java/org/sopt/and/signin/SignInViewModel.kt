package org.sopt.and.signin

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
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SignInSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SignInSideEffect>
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

    fun reversePasswordVisibility() {
        _state.value = _state.value.copy(
            isPasswordVisible = !_state.value.isPasswordVisible
        )
    }

    fun showSnackBar(signUpEmail: String, signUpPassword: String) {
        viewModelScope.launch {
            val toastMessage: String

            if (isSignInValid(signUpEmail, signUpPassword)) {
                toastMessage = "로그인에 성공했습니다."
                _sideEffect.emit(SignInSideEffect.NavigateToHome)
            } else {
                toastMessage = "로그인에 실패했습니다."
            }
            _sideEffect.emit(SignInSideEffect.ShowSnackBar(toastMessage))
        }
    }

    fun isSignInValid(signUpEmail: String, signUpPassword: String): Boolean {
        return (_state.value.email.isNotBlank() && _state.value.password.isNotBlank() && _state.value.email == signUpEmail && _state.value.password == signUpPassword)
    }
}
