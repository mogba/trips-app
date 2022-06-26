package com.example.finalapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finalapp.viewmodels.UserViewModel

@Composable
fun ResetPasswordScreen(navController: NavHostController) {
    Column() {
        val user: UserViewModel = viewModel()

        ResetPassword(
            user,
            onSuccessSendPasswordResetRequest = {
                navController.navigateUp()
            }
        )
    }
}

@Composable
fun ResetPassword(
    user: UserViewModel,
    onSuccessSendPasswordResetRequest: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current

        Column(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                OutlinedTextField(
                    value = user.email,
                    label = { Text(text = "E-mail") },
                    onValueChange = { user.email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Button(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Você receberá um e-mail para reiniciar a senha",
                            Toast.LENGTH_LONG
                        ).show()
                        onSuccessSendPasswordResetRequest()
                    }
                ) {
                    Text(text = "Enviar e-mail para reinício de senha")
                }
                Button(
                    onClick = {
                        onSuccessSendPasswordResetRequest()
                    }
                ) {
                    Text(text = "Voltar")
                }
            }
        }
    }
}