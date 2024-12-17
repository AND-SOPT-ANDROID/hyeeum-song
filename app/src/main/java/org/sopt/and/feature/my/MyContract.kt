package org.sopt.and.feature.my

class MyContract {
    data class MyState(
        val hobby: String = ""
    )

    sealed class MySideEffect {
        data class ShowToast(val toastMessage: Int) : MySideEffect()
    }
}
