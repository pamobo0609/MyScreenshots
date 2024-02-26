package com.jm.myscreenshots.screens.login

import com.jm.myscreenshots.screens.login.LoginState.Uninitialized
import com.jm.myscreenshots.screens.login.LoginState.Loaded
import com.jm.myscreenshots.base.BaseProcessor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.jm.myscreenshots.screens.login.LoginEvent.Initialize
import com.jm.myscreenshots.screens.login.LoginEvent.LoginClick
import com.jm.myscreenshots.screens.login.LoginEffect.NavigateToDetails
import com.jm.myscreenshots.screens.login.LoginEvent.DismissDialogClick

@HiltViewModel
class LoginProcessor @Inject constructor() : BaseProcessor<LoginEvent, LoginState, LoginEffect>() {

    override fun createInitialState() = Uninitialized

    override fun handleEvent(event: LoginEvent) {
        when (event) {
            Initialize -> onInitialize()
            is LoginClick -> onLoginClick(event.username, event.password)
            DismissDialogClick -> onDismissDialogClick()
        }
    }

    private fun onInitialize() {
        setState(Loaded())
    }

    private fun onLoginClick(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            setState((currentState as Loaded).copy(dialogState = DialogState.Alert))
        } else {
            emitEffect(NavigateToDetails)
        }
    }

    private fun onDismissDialogClick() {
        setState((currentState as Loaded).copy(dialogState = DialogState.None))
    }
}