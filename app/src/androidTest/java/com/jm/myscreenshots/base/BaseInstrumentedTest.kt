package com.jm.myscreenshots.base

import android.content.res.Resources
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.test.platform.app.InstrumentationRegistry
import io.mockk.mockk
import org.junit.Rule

open class BaseInstrumentedTest {

    @get:Rule
    val composableTestRule = createComposeRule()

    val resources: Resources = InstrumentationRegistry.getInstrumentation().targetContext.resources

    protected val navHostController: NavHostController = mockk(relaxed = true)
}
