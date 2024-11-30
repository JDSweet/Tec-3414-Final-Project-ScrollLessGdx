package com.gdx.scrollless.android.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class AddAlarmScreen  : AppScreen
{
    @Composable
    override fun BuildScreen()
    {
        // You can use Modifier.fillMaxSize() to fill the screen,
        // or you can use Modifier.size(newSize.dp) to change the size
        // (this function sets the width and height of the box to both
        // be the new size.
        //
        // You can also do Modifier.width(newWidth.dp) and Modifier.height(newHeight.dp)
        // to set the width and height of the box to different sizes.
        Box(modifier = Modifier.offset(0.dp, 20.dp).fillMaxSize())
        {
            Column {
                Row {
                    Text(text = "New Alarm")
                }
                Row {
                    var text by remember { mutableStateOf("Alarm") }
                    Text(text = "Name")
                    Spacer(modifier = Modifier.weight(1f))
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Insert Alarm Name") })
                }
                Row {
                    var text by remember { mutableStateOf("Alarm") }
                    Text(text = "Application")
                    Spacer(modifier = Modifier.weight(1f))
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Pick An Application") }
                    )
                }
                Row {
                    var text by remember { mutableStateOf("Alarm") }
                    Text(text = "Ringtone")
                    Spacer(modifier = Modifier.weight(1f))
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Select A Ringtone") }
                    )
                }
                Row {
                    Text(text = "Alarm Controls")
                }
                Row {
                    var text by remember { mutableStateOf("Alarm") }
                    Text(text = "Frequency")
                    Spacer(modifier = Modifier.weight(1f))
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Frequency of Alarm") }
                    )
                }
                Row {
                    var text by remember { mutableStateOf("Alarm") }
                    Text(text = "Category")
                    Spacer(modifier = Modifier.weight(1f))
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Application Category") }
                    )
                }

            }
        }
    }
}
