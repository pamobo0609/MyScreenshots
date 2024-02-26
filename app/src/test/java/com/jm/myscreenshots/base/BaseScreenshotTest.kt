package com.jm.myscreenshots.base

import app.cash.paparazzi.DeviceConfig
import org.junit.Rule
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.accessibility.AccessibilityRenderExtension

abstract class BaseScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL.copy(screenWidth = DeviceConfig.PIXEL.screenWidth * 2, softButtons = false),
        renderExtensions = setOf(AccessibilityRenderExtension())
    )
}
