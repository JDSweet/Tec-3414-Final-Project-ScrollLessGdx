package com.gdx.scrollless.android.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class AddAlarmScreen  : AppScreen
{
    @Composable
    override fun BuildScreen()
    {
        Box(modifier = Modifier.offset(0.dp, 20.dp))
        {
            Column {
                Row {
                    Text(text = "New Alarm")
                }
                Row {
                    var text by remember { mutableStateOf("Nicholas") }
                    Text(text = "Name")
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Insert Alarm Name") }
                    )
                }
            }
        }
    }
}
