package com.sefmat.picorlose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sefmat.picorlose.ui.theme.PicOrLoseTheme
import com.sefmat.picorlose.ui.theme.Login
import com.sefmat.picorlose.ui.theme.Navegation
import com.sefmat.picorlose.viewmodel.LoginVM
import com.sefmat.picorlose.ui.theme.Camera

class MainActivity : ComponentActivity() {

    private val viewModel = LoginVM()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PicOrLoseTheme {
                Navegation()
                //Camera()
                //Login(viewModel)
            }
        }
    }
}
