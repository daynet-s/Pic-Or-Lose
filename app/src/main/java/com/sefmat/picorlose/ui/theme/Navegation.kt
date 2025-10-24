package com.sefmat.picorlose.ui.theme

import android.Manifest
import android.graphics.Bitmap
import android.icu.text.CaseMap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File


data class Usuario(val nombre:String, val puntos:Int)

@Composable
fun Navegation() {
    val navController = rememberNavController()
    val loginViewModel = LoginVM()

    NavHost(navController, startDestination = "login") {
        composable("login") { Login(loginViewModel, navController) }
        composable("menu") { Menu(navController) }
        composable("ranking") { Rankings(navController) }
        composable("camera") { Camera(navController) }
    }
}

@Composable
fun Login(viewModel: LoginVM, navController: NavController) {
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

        Button(
            onClick = {
                if(viewModel.verifyLogin()) {
                    navController.navigate("menu")
                }
            }
        ) {
            Text("OK")
        }
    }
}


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
                navController.navigate("camera")
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
    val Usuarios = listOf(
        Usuario("fau37", 340),
        Usuario("polix", 335),
        Usuario("daDestroyer", 315),
        Usuario("User3928", 285),
        Usuario("Lux1", 280),
        Usuario("yoni", 245),
        Usuario("juliii", 240),
        Usuario("gus", 240),
        Usuario("dieg77", 235),
        Usuario("naxopro", 230)
    ).sortedByDescending { it.puntos }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("PUNTAJES GLOBALES")
        Spacer(modifier = Modifier.height(12.dp))

        // Codigo del ranking
        androidx.compose.foundation.lazy.LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            horizontalAlignment = Alignment.Start,
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp)
        ) {
            itemsIndexed(Usuarios) { index, usuario ->
                RankingRow(position = index + 1, usuario = usuario)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }



        Button(
            onClick = {
                navController.navigate("menu")
            }
        ) {
            Text("VOLVER")
        }
    }
}

@Composable
fun RankingRow(position: Int, usuario: Usuario) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$position. ${usuario.nombre}")
        Text(text = "${usuario.puntos} pts")
    }
}

@Composable
fun Camera(navController: NavController) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var permisoConcedido by remember { mutableStateOf(false) }

    val camLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && imageUri != null) {
            val bmp = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            bitmap = bmp
        }
    }

    val camPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        permisoConcedido = granted
        if (granted) {
            val file = File.createTempFile("taken_photo", ".jpg", context.cacheDir)
            file.deleteOnExit()
            val uri = FileProvider.getUriForFile(
                context,
                context.packageName + ".provider",
                file
            )
            imageUri = uri
            camLauncher.launch(uri)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap!!.asImageBitmap(),
                contentDescription = "FOTO TEMATICA",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = {
            camPermission.launch(Manifest.permission.CAMERA)
        }) {
            Text("TOMAR FOTO")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("menu")
        }) {
            Text("VOLVER")
        }
    }
}