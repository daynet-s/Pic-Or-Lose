package com.sefmat.picorlose.ui.theme

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import coil.compose.AsyncImage
import com.sefmat.picorlose.data.model.*
import com.sefmat.picorlose.repository.*
import com.sefmat.picorlose.viewmodel.*
import com.sefmat.picorlose.R
import java.io.File
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.room.util.TableInfo

@Composable
fun Navegation() {
    val navController = rememberNavController()
    val loginViewModel = LoginVM()
    val pictureVM = PictureVM()
    val userVM_api = UserVM_API()

    // INICIALIZA LA BASE DE DATOS DE USUARIOS **LOCAL** (Deshabilitado por el momento)
    /*val context = LocalContext.current

    val db by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "users_db"
        ).build()
    }
    val repo by lazy { UserRepository(db.userDao()) }
    val userVM by lazy { UserVM(repo) }*/

    NavHost(navController, startDestination = "login") {
        composable("login") { Login(loginViewModel, navController) }
        composable("signup") { SignUp(userVM_api, navController)}
        composable("menu") { Menu(navController) }
        composable("ranking") { Rankings(userVM_api,navController) }
        composable("userRanking") { MyInfo( userVM_api,navController) }
        composable("camera") { Camera(navController) }
        composable("todayphoto") { TodayPhotos(pictureVM, navController)}
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
            supportingText = { Text(viewModel.errorMsg.password, color = androidx.compose.ui.graphics.Color.Red)
            },
            visualTransformation = PasswordVisualTransformation()
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

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("signup")
            }
        ) {
            Text("REGISTRARSE")
        }
    }
}

@Composable
fun SignUp(viewModel: UserVM_API, navController: NavController) {
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val points by viewModel.points.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "CREAR CUENTA",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(value = username, onValueChange = { viewModel.username.value = it }, label = { Text("Nombre") })
        OutlinedTextField(value = password, onValueChange = { viewModel.password.value = it }, label = { Text("password") }, visualTransformation = PasswordVisualTransformation())
        //OutlinedTextField(value = points, onValueChange = { viewModel.points.value = it })

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.insertUser(
                UserAPI(
                    name = username,
                    passwrd = password,
                    points = points,
                    email = null
                )
            )

            viewModel.username.value = ""
            viewModel.password.value = ""
            viewModel.points.value = 0
            navController.navigate("login")
        }) {
            Text("REGISTRAR")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate("login")
            }
        ) {
            Text("VOLVER")
        }

        //Button(onClick = {viewModel.delAll()}) { Text("NUKE") } // BOTON DEBUG
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
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(300.dp)
        )

        Text("PIC OR LOSE")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Tema de hoy: SEMAFOROS")
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(R.drawable.semaforo),
            contentDescription = "Tema de Hoy",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(130.dp)
        )

        Button(
            onClick = {
                navController.navigate("camera")
            }
        ) {
            Text("JUGAR")
        }

        Button(
            onClick = {
                navController.navigate("userRanking")
            }
        ) {
            Text("MI INFORMACION")
        }

        Button(
            onClick = {
                navController.navigate("todayphoto")
            }
        ) {
            Text("FOTOS DEL DIA")
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
fun Rankings(viewModel: UserVM_API, navController: NavController) {
    val userInfo by viewModel.users.collectAsState()
    val sortUsers = userInfo.sortedByDescending { it.points }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("PUNTAJES GLOBALES")
        Spacer(modifier = Modifier.height(12.dp))

        // Codigo del ranking
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            horizontalAlignment = Alignment.Start,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            itemsIndexed(sortUsers) { index, user ->
                RankingRow(position = index + 1, user = user)
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
fun MyInfo(viewModel: UserVM_API,navController: NavController) {
    val userInfo by viewModel.users.collectAsState()

    if (userInfo.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Cargando...")
        }
    } else {
        val user = userInfo.random() // Al azar por ahora
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserItem(user) // SOLO 1 USUARIO

            Button(
                onClick = {
                    navController.navigate("menu")
                }
            ) {
                Text("VOLVER")
            }
        }
    }

}

@Composable
fun Camera(navController: NavController) {
    val context = LocalContext.current
    var howto by remember { mutableStateOf(false) }
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
        Text("TEMA:", style = MaterialTheme.typography.headlineMedium)
        Text("Semaforo", style = MaterialTheme.typography.bodyLarge)

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
            howto = true
        }) {
            Text("COMO JUGAR")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("menu")
        }) {
            Text("VOLVER")
        }

        if (howto) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text("INSTRUCCIONES:") },
                text = { Text("1. Presionar TOMAR FOTO\n2. Encontrar objeto de la tematica\n" +
                        "3. Capturarlo con la camara\n4. Subir la imagen para la comunidad") },
                confirmButton = {
                    Button(onClick = { howto = false }) { Text("OK") }
                }
            )
        }
    }
}

@Composable
fun TodayPhotos(viewModel: PictureVM = viewModel(), navController: NavController) {
    val pics by viewModel.pics.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Fotos del día", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        pics.forEach { pic ->
            Text("• ${pic.title}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            AsyncImage(
                model = pic.picture.url,
                contentDescription = pic.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        Button(onClick = {
            navController.navigate("menu")
        }) {
            Text("VOLVER")
        }
    }
}

// ELEMENTOS QUE NO SON PAGINAS PERO SON UTILIZADOS
@Composable
fun UserItem (user: UserAPI) {
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Nombre: ${user.name}")
            Text(text = "Puntaje: ${user.points}")
        }
    }
}

@Composable
fun RankingRow(position: Int, user: UserAPI) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$position. ${user.name}")
        Text(text = "${user.points} pts")
    }
}