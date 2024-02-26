package com.jm.myscreenshots.screenshots

import com.jm.myscreenshots.base.BaseScreenshotTest
import com.jm.myscreenshots.screens.login.LoginState
import com.jm.myscreenshots.screens.login.LoginView
import io.mockk.mockk
import org.junit.Test

class LoginScreenshotTest : BaseScreenshotTest() {

    @Test
    fun loginView() {
        paparazzi.snapshot {
            LoginView(
                state = LoginState.Loaded(),
                onLoginClick = mockk(relaxed = true),
                onDismissDialogClick = mockk(relaxed = true)
            )
        }
    }
}
