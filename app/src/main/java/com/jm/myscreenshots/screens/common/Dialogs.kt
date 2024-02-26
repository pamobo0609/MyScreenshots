package com.jm.myscreenshots.screens.common

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jm.myscreenshots.R
import com.jm.myscreenshots.screens.common.extensions.UnitFunction
import com.jm.myscreenshots.screens.common.extensions.get

@Composable
fun SimpleAlert(
    @StringRes confirmationButton: Int = R.string.ok,
    onConfirmClick: UnitFunction
) {
    AlertDialog(
        title = { Text(text = R.string.title_alert.get()) },
        text = { Text(text = R.string.something_happened.get()) },
        onDismissRequest = { },
        confirmButton = {
            Button(onClick = onConfirmClick) {
                Text(text = confirmationButton.get())
            }
        }
    )
}