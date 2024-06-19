package com.jm.myscreenshots.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jm.myscreenshots.R
import com.jm.myscreenshots.base.BaseInstrumentedTest
import com.jm.myscreenshots.screens.login.LoginScreen
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginViewTest : BaseInstrumentedTest() {

    @Before
    fun before() {
        composableTestRule.setContent { LoginScreen(navHostController = navHostController) }
    }

    @Test
    fun verify_all_views_exist() {
        composableTestRule.onNodeWithText("Welcome!").assertExists()
        composableTestRule.onNodeWithText(resources.getString(R.string.username)).assertExists()
        composableTestRule.onNodeWithText(resources.getString(R.string.password)).assertExists()
    }

    @Test
    fun when_username_invalid_then_show_alert_dialog() = runTest {
        composableTestRule.onNodeWithText(resources.getString(R.string.password)).performTextInput("password")
        composableTestRule.onNodeWithText(resources.getString(R.string.log_in)).performClick()
        composableTestRule.onNodeWithText(resources.getString(R.string.title_alert))
            .assertIsDisplayed()
    }

    @Test
    fun when_password_invalid_then_show_alert_dialog() {
        composableTestRule.onNodeWithText(resources.getString(R.string.username)).performTextInput("username")
        composableTestRule.onNodeWithText(resources.getString(R.string.log_in)).performClick()
        composableTestRule.onNodeWithText(resources.getString(R.string.title_alert))
            .assertIsDisplayed()
    }
}
