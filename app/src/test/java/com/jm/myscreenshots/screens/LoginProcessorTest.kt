package com.jm.myscreenshots.screens

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.jm.myscreenshots.base.BaseTest
import com.jm.myscreenshots.screens.login.LoginEffect
import com.jm.myscreenshots.screens.login.LoginEvent
import com.jm.myscreenshots.screens.login.LoginProcessor
import com.jm.myscreenshots.screens.login.LoginState
import com.jm.myscreenshots.screens.login.shouldShowAlert
import io.mockk.impl.annotations.InjectMockKs
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LoginProcessorTest :BaseTest() {

    @InjectMockKs
    lateinit var processor: LoginProcessor

    @Test
    fun `WHEN Initialize event THEN initialize`() = runTest {
        processor.handleEvent(LoginEvent.Initialize)
        processor.uiState.test {
            assertThat(awaitItem()).isInstanceOf(LoginState.Loaded::class.java)
        }
    }

    @Test
    fun `WHEN Log In click event AND empty username THEN show alert`() = runTest {
        processor.handleEvent(LoginEvent.Initialize)
        processor.handleEvent(LoginEvent.LoginClick("", ""))
        processor.uiState.test {
            val state = awaitItem()
            assertThat(state).isInstanceOf(LoginState.Loaded::class.java)
            assertThat((state as LoginState.Loaded).dialogState.shouldShowAlert()).isTrue()
        }
    }

    @Test
    fun `WHEN Log In click event THEN navigate to details`() = runTest {
        processor.handleEvent(LoginEvent.Initialize)
        processor.handleEvent(LoginEvent.LoginClick("username", "123"))
        processor.effects.test {
            assertThat(awaitItem()).isInstanceOf(LoginEffect.NavigateToDetails::class.java)
        }
    }

}
