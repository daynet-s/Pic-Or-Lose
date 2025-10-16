package com.sefmat.picorlose.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.sefmat.picorlose.viewmodel.LoginViewModel


@Composable
fun Login(viewModel: LoginViewModel) {
    //WIP
    var check by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*OutlinedTextField(
            //WIP
        )*/
        /*OutlinedTextField(
            //WIP
        )*/

        // WIP
        Button(
            onClick = {
                check = true
            }
        ) {
            Text("OK")
        }
    }
}