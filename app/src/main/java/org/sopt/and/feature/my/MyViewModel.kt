package org.sopt.and.feature.my

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.core.util.BaseViewModel
import org.sopt.and.core.util.LoadState
import org.sopt.and.domain.repository.MyRepository
import org.sopt.and.domain.usecase.MyUseCase
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val getHobbyUseCase: MyUseCase,
    private val MyRepository : MyRepository
) : BaseViewModel<MyContract.MyState, MyContract.MySideEffect, MyContract.MyEvent>() {

    override fun createInitialState(): MyContract.MyState = MyContract.MyState()

    override suspend fun handleEvent(intent: MyContract.MyEvent) {
        when (intent) {
            is MyContract.MyEvent.FetchMyHobby -> setState { copy(uiState = intent.uiState) }
        }
    }

    // usecase 사용했을 때,
    fun fetchUserHobby() {
        viewModelScope.launch {
            setIntent(
                MyContract.MyEvent.FetchMyHobby(uiState = LoadState.Loading, userInformation = currentState.profile)
            )
            getHobbyUseCase().onSuccess { profile->
                setIntent(
                    MyContract.MyEvent.FetchMyHobby(
                        uiState = LoadState.Success,
                        userInformation = profile
                    )
                )
            }.onFailure {
                setIntent(
                    MyContract.MyEvent.FetchMyHobby(
                        uiState = LoadState.Failure,
                        userInformation = currentState.profile
                    )
                )
                setSideEffect({ MyContract.MySideEffect.ShowToast(R.string.common_failure) })
            }
        }
    }

    // usecase 사용안했을 때,
    fun getUserHobbyRepository() {
        viewModelScope.launch {
            setIntent(
                MyContract.MyEvent.FetchMyHobby(uiState = LoadState.Loading, userInformation = currentState.profile)
            )
            MyRepository.getHobby().onSuccess {
                setIntent(
                    MyContract.MyEvent.FetchMyHobby(
                        uiState = LoadState.Success,
                        userInformation = currentState.profile
                    )
                )
            }.onFailure {
                setIntent(
                    MyContract.MyEvent.FetchMyHobby(
                        uiState = LoadState.Failure,
                        userInformation = currentState.profile
                    )
                )
                setSideEffect({ MyContract.MySideEffect.ShowToast(R.string.common_failure) })
            }
        }
    }
}
