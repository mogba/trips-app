package com.example.finalapp.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finalapp.components.PasswordField
import com.example.finalapp.viewmodels.UserViewModel
import com.example.finalapp.R
import com.example.finalapp.navigation.HOME_ROUTE
import com.example.finalapp.navigation.ScreenManager

@Composable
fun LoginScreen(navController: NavHostController) {
    Column() {
        val user: UserViewModel = viewModel()

        Login(
            user,
            onSuccessLoginRequest = {
                user.isSessionActive = true
                navController.navigate(HOME_ROUTE)
            },
            onRegisterRequest = {
                navController.navigate(ScreenManager.Register.route)
            },
            onResetPasswordRequest = {
                navController.navigate(ScreenManager.ResetPassword.route)
            }
        )
    }
}

@Composable
fun Login(
    user: UserViewModel,
    onSuccessLoginRequest: () -> Unit,
    onRegisterRequest: () -> Unit,
    onResetPasswordRequest: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.hypersonic_missiles),
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
        }
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                PasswordField(
                    value = user.password,
                    onChange = { user.password = it },
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
//                        if (user.email.equals("admin") && user.password.equals("admin")) {
                            onSuccessLoginRequest()
                            Toast.makeText(context, "Login válido.", Toast.LENGTH_LONG).show()
//                        }
//                        else {
//                            Toast.makeText(context, "Login inválido.", Toast.LENGTH_LONG).show()
//                        }
                    }
                ) {
                    Text(text = "Entrar")
                }

                ClickableText(
                    text = AnnotatedString("Cadastrar"),
                    onClick = {
                        onRegisterRequest()
                    }
                )

                ClickableText(
                    text = AnnotatedString("Esqueci minha senha"),
                    onClick = {
                        onResetPasswordRequest()
                    }
                )
            }
        }
    }
}