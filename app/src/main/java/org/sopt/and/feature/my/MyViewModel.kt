package org.sopt.and.feature.my

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.domain.repository.WavveRepository
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val wavveRepository: WavveRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MyState())
    val state: StateFlow<MyState>
        get() = _state.asStateFlow()

    fun getUserHobby() {
        viewModelScope.launch {
            wavveRepository.getHobby().onSuccess { hobbyEntity ->
                _state.value = _state.value.copy(
                    hobby = hobbyEntity.hobby
                )
            }.onFailure { }
        }
    }
}
