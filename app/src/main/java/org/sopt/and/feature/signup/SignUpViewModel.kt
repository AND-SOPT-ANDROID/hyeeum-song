package org.sopt.and.feature.signup

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
import org.sopt.and.domain.entity.request.RequestSignUpEntity
import org.sopt.and.domain.usecase.SignUpUseCase
import org.sopt.and.feature.signup.SignUpContract.SignUpEvent
import org.sopt.and.feature.signup.SignUpContract.SignUpSideEffect
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpContract.SignUpState())
    val state: StateFlow<SignUpContract.SignUpState>
        get() = _state.asStateFlow()
    private val currentState: SignUpContract.SignUpState
        get() = state.value

    private val _sideEffect: MutableSharedFlow<SignUpSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SignUpSideEffect>
        get() = _sideEffect.asSharedFlow()

    private fun setState(reduce: SignUpContract.SignUpState.() -> SignUpContract.SignUpState) {
        _state.value = currentState.reduce()
    }

    fun setEvent(event: SignUpEvent) {
        dispatchEvent(event)
    }

    private fun dispatchEvent(event: SignUpEvent) = viewModelScope.launch {
        handleEvent(event)
    }

    private fun handleEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.SetUsername -> {
                setState {
                    copy(username = event.username)
                }
            }

            is SignUpEvent.SetPassword -> {
                setState {
                    copy(password = event.password)
                }
            }
            is SignUpEvent.SetHobby -> {
                setState {
                    copy(hobby = event.hobby)
                }
            }
        }
    }

    fun isSignUpValid() {
        viewModelScope.launch {
            if (isUsernameValid() && isPasswordValid() && isHobbyValid()) {
                signUpUseCase.invoke(
                    RequestSignUpEntity(
                        username = _state.value.username,
                        password = _state.value.password,
                        hobby = _state.value.hobby
                    )
                ).onSuccess {
                    _sideEffect.emit(SignUpSideEffect.NavigateToSignIn)
                }
                    .onFailure {
                        _sideEffect.emit(SignUpSideEffect.ShowToast(R.string.common_failure))
                    }
            } else {
                val toastMessage = when {
                    !isUsernameValid() -> R.string.username_condition
                    !isPasswordValid() -> R.string.password_condition
                    !isHobbyValid() -> R.string.hobby_condition
                    else -> R.string.not_valid_input
                }
                _sideEffect.emit(SignUpSideEffect.ShowToast(toastMessage))
            }
        }
    }

    fun isUsernameValid(): Boolean {
        return _state.value.username.length in MIN_SIGNUP_LENGTH..MAX_SIGNUP_LENGTH
    }

    fun isPasswordValid(): Boolean {
        val password = _state.value.password
        return Pattern.matches(PASSWORD_CONDITION, password)
    }

    fun isHobbyValid(): Boolean {
        return _state.value.hobby.length in MIN_SIGNUP_LENGTH..MAX_SIGNUP_LENGTH
    }

    fun reversePasswordVisibility() {
        _state.value = _state.value.copy(
            isPasswordVisible = !_state.value.isPasswordVisible
        )
    }

    companion object {
        private const val MIN_SIGNUP_LENGTH = 1
        private const val MAX_SIGNUP_LENGTH = 8
        private const val PASSWORD_CONDITION =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{$MIN_SIGNUP_LENGTH,$MAX_SIGNUP_LENGTH}$"
    }
}
