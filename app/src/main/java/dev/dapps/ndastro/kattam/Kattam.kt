package dev.dapps.ndastro.kattam

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.dapps.ndastro.R
import dev.dapps.ndastro.ui.theme.NDAstroPreview
import dev.dapps.ndastro.ui.theme.NDAstroTheme
import dev.dapps.ndastro.utils.KattamDetailTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KattamView(
    onEditKattam: (String) -> Unit,
    onBack: () -> Unit,
    onDeleteKattam: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }

    ) {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = { KattamDetailTopAppBar(onBack = onBack, onDelete = {}) },
            floatingActionButton = {
                SmallFloatingActionButton(onClick = { onEditKattam("") }) {
                    Icon(Icons.Filled.Edit, stringResource(id = R.string.edit_kattam))
                }
            }
        ) { Kattam() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Kattam(
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Kattam")
        }
    }
}

@Composable
@NDAstroPreview
private fun KattamPreview() {
    NDAstroTheme {
        Surface {
            Kattam()
        }
    }
}
