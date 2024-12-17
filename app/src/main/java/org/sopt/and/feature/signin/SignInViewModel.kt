package org.sopt.and.feature.signin

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
import org.sopt.and.domain.entity.request.RequestSignInEntity
import org.sopt.and.domain.usecase.SignInUseCase
import org.sopt.and.sharedpreference.User
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val user: User,
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState>
        get() = _state.asStateFlow()
    private val currentState: SignInState
        get() = state.value

    private val _sideEffect: MutableSharedFlow<SignInSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SignInSideEffect>
        get() = _sideEffect.asSharedFlow()

    private fun setState(reduce: SignInState.() -> SignInState) {
        _state.value = currentState.reduce()
    }

    fun setEvent(event: SignInEvent) {
        dispatchEvent(event)
    }

    private fun dispatchEvent(event: SignInEvent) = viewModelScope.launch {
        handleEvent(event)
    }

    private fun handleEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.SetUsername -> {
                setState {
                    copy(username = event.username)
                }
            }

            is SignInEvent.SetPassword -> {
                setState {
                    copy(password = event.password)
                }
            }
        }
    }

    fun reversePasswordVisibility() {
        _state.value = _state.value.copy(
            isPasswordVisible = !_state.value.isPasswordVisible
        )
    }

    fun isSignInValid() {
        viewModelScope.launch {
            var toastMessage: String = ""
            signInUseCase.invoke(
                RequestSignInEntity(
                    username = _state.value.username,
                    password = _state.value.password
                )
            ).onSuccess { SignInEntity ->
                toastMessage = "로그인에 성공했습니다."
                user.saveUserToken(SignInEntity.token)
                user.setSignInState(true)
                _sideEffect.emit(SignInSideEffect.NavigateToHome)
            }.onFailure {
                toastMessage = "로그인에 실패했습니다."
            }
            _sideEffect.emit(SignInSideEffect.ShowSnackBar(toastMessage))
        }
    }
}
