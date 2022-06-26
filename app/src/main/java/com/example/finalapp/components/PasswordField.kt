package com.example.finalapp.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.Modifier

@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
    label: String = "Senha",
    modifier: Modifier = Modifier
) {
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    var trailingIcon =
        if (passwordVisibility) Icons.Filled.VisibilityOff
        else Icons.Filled.Visibility
    var visualTransformation =
        if (passwordVisibility) VisualTransformation.None
        else PasswordVisualTransformation()

    OutlinedTextField(
        value = value,
        onValueChange = { s -> onChange(s) },
        label = {
            Text(text = label)
        },
        visualTransformation = visualTransformation,
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = ""
                )
            }
        },
        modifier = modifier
    )
}
