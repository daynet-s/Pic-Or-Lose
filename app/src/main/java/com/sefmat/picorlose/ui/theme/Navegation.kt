package com.sefmat.picorlose.ui.theme

import android.icu.text.CaseMap
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sefmat.picorlose.viewmodel.LoginVM

@Composable
fun Navegation() {
    val navController = rememberNavController()
    val loginViewModel = LoginVM()

    NavHost(navController, startDestination = "login") {
        composable("login") { Login(loginViewModel, navController)} //????
        /*composable("menu") {  }
        composable("ranking") {  }
        composable("camera") {  }*/
    }
}

@Composable // AVERIGUAR COMO HACER QUE FUNCIONE CON EL VM
fun Login(viewModel: LoginVM, navController: NavController) {
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

// WIP
@Composable
fun Menu(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("PIC OR LOSE")
        Text("Tema de hoy: SEMAFOROS")

        Button(
            onClick = {

            }
        ) {
            Text("JUGAR")
        }

        Button(
            onClick = {

            }
        ) {
            Text("PUNTAJES")
        }
    }
}

@Composable
fun Rankings(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("PUNTAJES GLOBALES")

        // Aqui iria el ranking

        Button(
            onClick = {

            }
        ) {
            Text("VOLVER")
        }
    }
}