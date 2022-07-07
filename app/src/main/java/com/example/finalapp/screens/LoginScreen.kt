package com.example.finalapp.screens

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.api.finalapp.model.User
import com.example.finalapp.components.PasswordField
import com.example.finalapp.viewmodels.UserViewModel
import com.example.finalapp.R
import com.example.finalapp.components.Message
import com.example.finalapp.components.TextField
import com.example.finalapp.navigation.HOME_ROUTE
import com.example.finalapp.navigation.ScreenManager
import com.example.finalapp.viewmodels.UserViewModelFactory

@Composable
fun LoginScreen(navController: NavHostController) {
    Column {
        val context = LocalContext.current
        val app = context.applicationContext as Application

        val userModel: UserViewModel = viewModel(factory = UserViewModelFactory(app))

        Login(
            userModel,
            handleLogin = { user ->
                if (user == null) {
                    val text =
                        "Nenhum usuário encontrado. " +
                        "Verifique se as credenciais foram " +
                        "informadas corretamente."

                    Message(context, text)
                } else {
                    Message(context, "Olá ${user.name}")

                    userModel.isSessionActive = true
                    navController.navigate(HOME_ROUTE)
                }
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
    userModel: UserViewModel,
    handleLogin: (User?) -> Unit,
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
                TextField(
                    value = userModel.email,
                    onChange = { userModel.email = it },
                    label = "E-mail",
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                PasswordField(
                    value = userModel.password,
                    onChange = { userModel.password = it },
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
                        if (userModel.isValidForLogin()) {
                            userModel.login(handleLogin)
                        } else {
                            Message(context, "Informe suas credenciais para entrar")
                        }
                    }
                ) {
                    Text(text = "Entrar")
                }

                Spacer(modifier = Modifier.height(20.dp))

                ClickableText(
                    text = AnnotatedString("Cadastre-se"),
                    onClick = {
                        onRegisterRequest()
                    }
                )
                
                Spacer(modifier = Modifier.height(10.dp))

                ClickableText(
                    text = AnnotatedString("Esqueceu sua senha?"),
                    onClick = {
                        onResetPasswordRequest()
                    }
                )
            }
        }
    }
}