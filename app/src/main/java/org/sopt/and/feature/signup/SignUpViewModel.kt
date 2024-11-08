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
import org.sopt.and.domain.repository.WavveRepository
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val wavveRepository: WavveRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SignUpSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SignUpSideEffect>
        get() = _sideEffect.asSharedFlow()

    fun setUsername(username: String) {
        _state.value = _state.value.copy(
            username = username
        )
    }

    fun setPassword(password: String) {
        _state.value = _state.value.copy(
            password = password
        )
    }

    fun setHobby(hobby: String) {
        _state.value = _state.value.copy(
            hobby = hobby
        )
    }

    fun isSignUpValid() {
        viewModelScope.launch {
            if (isUsernameValid() && isPasswordValid() && isHobbyValid()) {
                wavveRepository.createUser(
                    RequestSignUpEntity(
                        username = _state.value.username,
                        password = _state.value.password,
                        hobby = _state.value.hobby
                    )
                ).onSuccess {
                    _sideEffect.emit(SignUpSideEffect.NavigateToSignIn)
                }
                    .onFailure {
                        //TODO
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
        return _state.value.username.length in MIN_SIGNUP_LENGTH .. MAX_SIGNUP_LENGTH
    }

    fun isPasswordValid(): Boolean {
        val password = _state.value.password
        return Pattern.matches(PASSWORD_CONDITION, password)
    }

    fun isHobbyValid(): Boolean {
        return _state.value.hobby.length in MIN_SIGNUP_LENGTH .. MAX_SIGNUP_LENGTH
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
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{$MIN_SIGNUP_LENGTH,$MAX_SIGNUP_LENGTH}$"    }
}
