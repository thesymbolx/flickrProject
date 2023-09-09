package com.job.ui.theme.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.job.ui.R

@Composable
fun ErrorDialog() {
    val openDialog = remember { mutableStateOf(false)  }

    AlertDialog(
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Text(text = stringResource(id = R.string.errorDialogTitle))
        },
        text = {
            Text(stringResource(id = R.string.errorDialogMessage))
        },
        confirmButton = {
            Button(
                onClick = {
                    openDialog.value = false
                }) {
                Text(stringResource(id = R.string.ok))
            }
        }
    )
}