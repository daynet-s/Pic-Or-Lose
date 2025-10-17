package com.sefmat.picorlose.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sefmat.picorlose.viewmodel.LoginVM


@Composable
fun Login(viewModel: LoginVM) {
    //WIP
    var check by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar Sesion")

        OutlinedTextField(
            value = viewModel.login.username,
            onValueChange =  { viewModel.login.username = it },
            label = { Text("Ingrese nombre de usuario") },
            isError = !viewModel.verifyName()
        )
        OutlinedTextField(
            value = viewModel.login.password,
            onValueChange =  { viewModel.login.password = it },
            label = { Text("Ingrese contraseña") },
            isError = !viewModel.verifyPassword()
            // Agregar linea para ocultar la contraseña
        )

        // WIP
        Button(
            enabled = viewModel.verifyLogin(),
            onClick = {
                if(viewModel.verifyLogin()) {
                    check = true
                }
            }
        ) {
            Text("OK")
        }

        if (check) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text("WIP") },
                text = { Text("ESTO LLEVARA AL MENU") },
                confirmButton = {
                    Button(onClick = { check = false }) { Text("OK") }
                }
            )
        }
    }
}