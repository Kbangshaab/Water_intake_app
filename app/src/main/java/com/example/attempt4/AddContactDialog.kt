package com.example.attempt4

import android.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactDialog(
    state: ContactState,
    onEvent: (ContactEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {
            onEvent(ContactEvent.HideDialog)
        }){
        Column(Modifier.background(color = Color.White)) {
            Text(text = "Add water intake")
            TextField(
                value = state.waterIntake,
                onValueChange = { onEvent(ContactEvent.SetWaterIntake(it)) },
                placeholder = { Text(text = "Enter water intake") }
            )
            TextField(
                value = state.dateToday,
                onValueChange = { onEvent(ContactEvent.SetDateToday(it)) },
                placeholder = { Text(text = "Date") }
            )
            TextField(
                value = state.timeToday,
                onValueChange = { onEvent(ContactEvent.SetTimeToday(it)) },
                placeholder = { Text(text = "Time") }
            )




            Button(onClick ={onEvent(ContactEvent.SaveContact)},modifier = Modifier.fillMaxWidth()) {
                Text(text = "Add")

            }
        }
    }
}