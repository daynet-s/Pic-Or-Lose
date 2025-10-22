package com.sefmat.picorlose.ui.theme

import android.icu.text.CaseMap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sefmat.picorlose.R
import com.sefmat.picorlose.viewmodel.LoginVM

@Composable
fun Navegation() {
    val navController = rememberNavController()
    val loginViewModel = LoginVM()

    NavHost(navController, startDestination = "login") {
        composable("login") { Login(loginViewModel, navController) }
        composable("menu") { Menu(navController) }
        composable("ranking") { Rankings(navController) }
        //composable("camera") {  }
    }
}

//AVERIGUAR COMO USAR MENSAJES DE ERROR
@Composable
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
            onValueChange =  { newText -> viewModel.login.username = newText },
            label = { Text("Ingrese nombre de usuario") },
            isError = !viewModel.verifyName(),
            supportingText = { Text(viewModel.errorMsg.username, color = androidx.compose.ui.graphics.Color.Red) }
        )
        OutlinedTextField(
            value = viewModel.login.password,
            onValueChange =  { newText -> viewModel.login.password = newText },
            label = { Text("Ingrese contraseña") },
            isError = !viewModel.verifyPassword(),
            supportingText = { Text(viewModel.errorMsg.password, color = androidx.compose.ui.graphics.Color.Red) }
            // Agregar linea para ocultar la contraseña
        )

        // WIP
        Button(
            /*enabled = viewModel.verifyLogin(),*/
            onClick = {
                if(viewModel.verifyLogin()) {
                    //check = true
                    navController.navigate("menu")
                }
            }
        ) {
            Text("OK")
        }

        /*if (check) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text("WIP") },
                text = { Text("ESTO LLEVARA AL MENU") },
                confirmButton = {
                    Button(onClick = { check = false }) { Text("OK") }
                }
            )
        }*/
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
        Image(
            painter = painterResource(R.drawable.camera),
            contentDescription = "TEST",
            contentScale = ContentScale.Fit
        )

        Text("PIC OR LOSE")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Tema de hoy: SEMAFOROS")
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // BOTON QUE LLEVE A CAMARA
            }
        ) {
            Text("JUGAR")
        }

        Button(
            onClick = {
                navController.navigate("ranking")
            }
        ) {
            Text("PUNTAJES")
        }

        Spacer(modifier = Modifier.height(84.dp))

        Button(
            onClick = {
                navController.navigate("login")
            }
        ) {
            Text("CERRAR SESION")
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
        Spacer(modifier = Modifier.height(12.dp))

        // Aqui iria el ranking

        Button(
            onClick = {
                navController.navigate("menu")
            }
        ) {
            Text("VOLVER")
        }
    }
}