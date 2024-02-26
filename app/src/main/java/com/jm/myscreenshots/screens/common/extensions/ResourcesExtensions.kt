package com.jm.myscreenshots.screens.common.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun Int.get() = stringResource(id = this)