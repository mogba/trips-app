package com.example.finalapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun DropDownField(
    label: String,
    options: List<String>,
    value: String,
    onValueChange: (String) -> Unit,
) {
    var isDropDownExpanded by remember { mutableStateOf(false) }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon =
        if (isDropDownExpanded) Icons.Filled.ArrowDropUp
        else Icons.Filled.ArrowDropDown

    Box {
        OutlinedTextField(
            value = value,
            label = { Text(text = label) },
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(
                    icon,
                    null,
                    Modifier.clickable {
                        isDropDownExpanded = !isDropDownExpanded
                    }
                )
            },
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = {
                    isDropDownExpanded = !isDropDownExpanded
                }),
        )
        DropdownMenu(
            expanded = isDropDownExpanded,
            onDismissRequest = {
                isDropDownExpanded = false
            },
            modifier = Modifier
                .width(with(LocalDensity.current) {
                    textFieldSize.width.toDp()
                })
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onValueChange(option)
                    isDropDownExpanded = false
                }) {
                    Text(text = option)
                }
            }
        }
    }
}