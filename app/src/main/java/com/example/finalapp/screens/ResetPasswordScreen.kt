package com.example.finalapp.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finalapp.components.Message
import com.example.finalapp.components.NavigationTopAppBar
import com.example.finalapp.components.TextField
import com.example.finalapp.viewmodels.UserViewModel
import com.example.finalapp.viewmodels.UserViewModelFactory

@Composable
fun ResetPasswordScreen(navController: NavHostController) {
    val context = LocalContext.current
    val app = context.applicationContext as Application

    val userModel: UserViewModel = viewModel(factory = UserViewModelFactory(app))

    Scaffold(
        topBar = {
            NavigationTopAppBar(navController, "Redefinir senha")
        }
    ) {
        Column(
            verticalArrangement = Arrangement
                .spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    TextField(
                        value = userModel.email,
                        label = "E-mail",
                        onChange = { userModel.email = it },
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
                            userModel.sendResetPasswordEmail()
                            Message(
                                context,
                                "Você receberá um e-mail para redefinir a senha",
                            )
                            navController.navigateUp()
                        }
                    ) {
                        Text(text = "Enviar e-mail para redefinir senha")
                    }
                }
            }
        }
    }
}