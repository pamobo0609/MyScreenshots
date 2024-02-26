package com.jm.myscreenshots.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jm.myscreenshots.screens.MyScreenshotsApp
import com.jm.myscreenshots.screens.MyScreenshotsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            MyScreenshotsTheme {
                MyScreenshotsApp()
            }
        }
    }

//    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        installSplashScreen()
//        super.onCreate(savedInstanceState, persistentState)
//
//        setContent {
//            MyScreenshotsTheme {
//                MyScreenshotsApp()
//            }
//        }
//    }
}
