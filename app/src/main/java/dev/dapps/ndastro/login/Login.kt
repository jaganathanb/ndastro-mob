package dev.dapps.ndastro.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.dapps.ndastro.ui.theme.NDAstroPreview
import dev.dapps.ndastro.ui.theme.NDAstroTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView() {
    Login()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Login")
        }
    }
}

@Composable
@NDAstroPreview
private fun LoginPreview() {
    NDAstroTheme {
        Surface {
            Login()
        }
    }
}
