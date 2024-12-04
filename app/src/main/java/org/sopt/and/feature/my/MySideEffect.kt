package org.sopt.and.feature.my

sealed class MySideEffect {
    data class ShowToast(val toastMessage: Int) : MySideEffect()
}
