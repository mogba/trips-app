package com.example.finalapp.components

import android.app.DatePickerDialog
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerField(
    label: String,
    value: String,
    onChange: (String) -> Unit,
) {
    val context = LocalContext.current

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    var date = remember { mutableStateOf("") }
    date.value = value

    val dateObject =
        if (!value.isNullOrBlank()) LocalDate.parse(value, formatter)
        else LocalDate.now()

    val year: Int = dateObject.year
    val month: Int = dateObject.monthValue
    val day: Int = dateObject.dayOfMonth

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, newYear: Int, newMonth: Int, newDayOfMonth: Int ->
            date.value = LocalDate.of(newYear, newMonth, newDayOfMonth).format(formatter)
            onChange(date.value)
        },
        year,
        month,
        day,
    )

    Box(
        Modifier
            .padding(top = 20.dp)
            .clickable { datePickerDialog.show() }
    ) {
        OutlinedTextField(
            value = date.value,
            onValueChange = {},
            singleLine = true,
            enabled = false,
            label = { Text(text = label) },
            modifier = Modifier
                .padding(bottom = 0.dp)
                .fillMaxWidth()
                .clickable { datePickerDialog.show() },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.CalendarToday,
                    contentDescription = null,
                    modifier = Modifier.padding(16.dp)
                )
            }
        )
    }
}