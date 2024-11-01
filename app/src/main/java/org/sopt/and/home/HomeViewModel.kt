package org.sopt.and.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.sopt.and.R
import org.sopt.and.home.model.ContentModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    init {
        setTopBannerContent()
        setRecommendContent()
        setTop20Content()
    }

    private fun setTopBannerContent() {
        _state.value = _state.value.copy(
            topBannerContent = persistentListOf(
                ContentModel(
                    R.drawable.wavve_top_banner,
                    1
                ),
                ContentModel(
                    R.drawable.wavve_banner1,
                    2
                ),
                ContentModel(
                    R.drawable.wavve_banner2,
                    3
                )
            )
        )
    }

    private fun setRecommendContent() {
        _state.value = _state.value.copy(
            recommendContent = persistentListOf(
                ContentModel(
                    R.drawable.wavve_banner1
                ),
                ContentModel(
                    R.drawable.wavve_banner2
                ),
                ContentModel(
                    R.drawable.wavve_banner3
                ),
            )
        )
    }

    private fun setTop20Content() {
        _state.value = _state.value.copy(
            top20Content = persistentListOf(
                ContentModel(
                    R.drawable.wavve_banner1,
                    1
                ),
                ContentModel(
                    R.drawable.wavve_banner2,
                    2
                ),
                ContentModel(
                    R.drawable.wavve_banner3,
                    3
                ),
            )
        )
    }
}
