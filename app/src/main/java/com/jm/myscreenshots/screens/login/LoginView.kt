package com.jm.myscreenshots.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jm.myscreenshots.R
import com.jm.myscreenshots.screens.Screen
import com.jm.myscreenshots.screens.common.SimpleAlert
import com.jm.myscreenshots.screens.common.extensions.UnitFunction
import com.jm.myscreenshots.screens.common.extensions.get
import com.jm.myscreenshots.screens.login.LoginEffect.NavigateToDetails
import com.jm.myscreenshots.screens.login.LoginEvent.DismissDialogClick
import com.jm.myscreenshots.screens.login.LoginEvent.Initialize
import com.jm.myscreenshots.screens.login.LoginEvent.LoginClick
import com.jm.myscreenshots.screens.login.LoginState.Loaded
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

private const val InitializationKey = "initKey"

@Composable
fun LoginScreen(navHostController: NavHostController) {
    with(viewModel<LoginProcessor>()) {
        val uiState = uiState.collectAsStateWithLifecycle()
        LoginView(
            state = uiState.value,
            onLoginClick = { username, password -> emitEvent(LoginClick(username, password)) },
            onDismissDialogClick = { emitEvent(DismissDialogClick) }
        )

        LaunchedEffect(Unit) {
            effects.collect { effect ->
                when (effect) {
                    NavigateToDetails -> navHostController.navigate(Screen.Details.route)
                }
            }
        }

        LaunchedEffect(InitializationKey) { emitEvent(Initialize) }
    }
}

@Composable
fun LoginView(
    state: LoginState,
    onLoginClick: (String, String) -> Unit,
    onDismissDialogClick: UnitFunction
) {
    when (state) {
        is Loaded -> LoginLoadedView(
            state = state,
            onLoginClick = onLoginClick,
            onDismissDialogClick = onDismissDialogClick
        )
        else -> Unit
    }
}

@Composable
private fun LoginLoadedView(
    state: Loaded,
    onLoginClick: (String, String) -> Unit,
    onDismissDialogClick: UnitFunction
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Welcome!")
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = R.string.username.get()) }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = R.string.password.get()) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = R.string.toggle_password.get()
                        )
                    }
                } else {
                    IconButton(onClick = { showPassword = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = R.string.toggle_password.get()
                        )
                    }
                }
            }
        )
        Button(
            shape = ButtonDefaults.elevatedShape,
            onClick = { onLoginClick(username, password) },
        ) {
            Text(text = R.string.log_in.get())
        }
    }

    if (state.dialogState.shouldShowAlert()) {
        SimpleAlert(onConfirmClick = onDismissDialogClick)
    }
}