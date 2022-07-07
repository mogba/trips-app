package com.example.finalapp.screens

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finalapp.components.Message
import com.example.finalapp.components.PasswordField
import com.example.finalapp.components.TextField
import com.example.finalapp.viewmodels.UserViewModel
import com.example.finalapp.viewmodels.UserViewModelFactory

@Composable
fun RegisterScreen(navController: NavHostController) {
    Column {
        val context = LocalContext.current
        val app = context.applicationContext as Application

        val userModel: UserViewModel = viewModel(factory = UserViewModelFactory(app))

        val columnModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Cadastre-se") },
                    navigationIcon = if (navController.previousBackStackEntry != null) {
                        {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Voltar"
                                )
                            }
                        }
                    } else {
                        null
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Column(modifier = columnModifier) {
                    TextField(
                        value = userModel.name,
                        onChange = { userModel.name = it },
                        label = "Nome *",
                    )
                }
                Column(modifier = columnModifier) {
                    TextField(
                        value = userModel.email,
                        onChange = { userModel.email = it },
                        label = "E-mail *",
                    )
                }
                Column(modifier = columnModifier) {
                    PasswordField(
                        value = userModel.password,
                        onChange = { userModel.password = it },
                        label = "Senha *",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = columnModifier
                ) {
                    Button(
                        onClick = {
                            if (userModel.isValidForRegister()) {
                                userModel.register()

                                Message(
                                    context,
                                    "Cadastro efetuado",
                                )

                                navController.navigateUp()
                            } else {
                                Message(
                                    context,
                                    "Preencha os campos obrigatórios.",
                                )
                            }
                        },
                        modifier = Modifier
                            .height(65.dp)
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                    ) {
                        Text(text = "Cadastrar")
                    }
                }
            }
        }
    }
}