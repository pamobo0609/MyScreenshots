package com.jm.myscreenshots.screens.login

import com.jm.myscreenshots.base.BaseEffect
import com.jm.myscreenshots.base.BaseEvent
import com.jm.myscreenshots.base.BaseState

sealed class LoginEvent : BaseEvent() {
    data object Initialize : LoginEvent()

    data class LoginClick(
        val username: String,
        val password: String
    ) : LoginEvent()

    data object DismissDialogClick : LoginEvent()
}

sealed class LoginState : BaseState() {
    data object Uninitialized : LoginState()
    data class Loaded(
        val dialogState: DialogState = DialogState.None
    ) : LoginState()
}

sealed class LoginEffect : BaseEffect() {
    data object NavigateToDetails : LoginEffect()
}

enum class DialogState { None, Alert }

fun DialogState.shouldShowAlert() = equals(DialogState.Alert)
