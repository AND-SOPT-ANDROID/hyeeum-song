package org.sopt.and.feature.my

import org.sopt.and.core.util.LoadState
import org.sopt.and.core.util.UiEvent
import org.sopt.and.core.util.UiSideEffect
import org.sopt.and.core.util.UiState
import org.sopt.and.domain.entity.response.ResponseHobbyEntity

class MyContract {
    data class MyState(
        val uiState: LoadState = LoadState.Loading,
        val profile: ResponseHobbyEntity = ResponseHobbyEntity()
    ) : UiState

    sealed class MySideEffect : UiSideEffect {
        data class ShowToast(val toastMessage: Int) : MySideEffect()
    }

    sealed class MyEvent : UiEvent {
        data class FetchMyHobby(
            val uiState: LoadState,
            val userInformation: ResponseHobbyEntity
        ) : MyEvent()
    }
}
