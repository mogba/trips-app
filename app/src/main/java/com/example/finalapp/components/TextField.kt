package com.example.finalapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun TextField(
    value: String,
    onChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isNumberInput: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        label = { Text(text = label) },
        onValueChange = { s -> onChange(s) },
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        keyboardOptions =
            if (isNumberInput) KeyboardOptions(keyboardType = KeyboardType.Number)
            else KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}