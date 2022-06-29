package com.example.finalapp.screens

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
import com.example.finalapp.components.PasswordField
import com.example.finalapp.components.TextField
import com.example.finalapp.viewmodels.UserViewModel

@Composable
fun RegisterScreen(navController: NavHostController) {
    Column() {
        val user: UserViewModel = viewModel()

        val context = LocalContext.current

        val columnModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Criar cadastro") },
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
                        value = user.username,
                        onChange = { user.username = it },
                        label = "Usuário",
                    )
                }
                Column(modifier = columnModifier) {
                    TextField(
                        value = user.email,
                        onChange = { user.email = it },
                        label = "E-mail",
                    )
                }
                Column(modifier = columnModifier) {
                    PasswordField(
                        value = user.password,
                        onChange = { user.password = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    )
                }
                Column(modifier = columnModifier) {
                    PasswordField(
                        label = "Confirmar senha",
                        value = user.passwordConfirmation,
                        onChange = { user.passwordConfirmation = it },
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
                            Toast.makeText(
                                context,
                                "Registro criado com sucesso",
                                Toast.LENGTH_LONG
                            ).show()
                            navController.navigateUp()
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