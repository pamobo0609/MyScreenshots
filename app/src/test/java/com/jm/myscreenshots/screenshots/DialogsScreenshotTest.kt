package com.jm.myscreenshots.screenshots

import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.detectEnvironment
import com.jm.myscreenshots.screens.common.SimpleAlert
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class DialogsScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        environment = detectEnvironment().run {
            copy(
                compileSdkVersion = 23,
                platformDir = platformDir.replace("34", "33")
            )
        }
    )

    @Test
    fun simpleAlertDialog() {
        paparazzi.snapshot {
            SimpleAlert(onConfirmClick = mockk(relaxed = true))
        }
    }
}
