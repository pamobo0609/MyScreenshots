package com.jm.myscreenshots.base

import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule

abstract class BaseTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun before() {
        MockKAnnotations.init(this)
    }

}