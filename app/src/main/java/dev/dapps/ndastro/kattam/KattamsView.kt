package dev.dapps.ndastro.kattam

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.unit.dp
import dev.dapps.ndastro.R
import dev.dapps.ndastro.data.Kattam
import dev.dapps.ndastro.ui.theme.NDAstroPreview
import dev.dapps.ndastro.ui.theme.NDAstroTheme
import dev.dapps.ndastro.utils.NdAstroTopAppBar
import dev.dapps.ndastro.utils.SvgImageFromApi
import java.time.ZoneOffset
import java.time.ZonedDateTime


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun KattamsView(
    @StringRes userMessage: Int,
    onAddKattamClick: () -> Unit,
    onKattamClick: (Kattam) -> Unit,
    onUserMessageDisplayed: () -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            NdAstroTopAppBar(
                openDrawer = openDrawer,
                onFilterAllTasks = {  },
                onFilterActiveTasks = {  },
                onFilterCompletedTasks = {  },
                onClearCompletedTasks = {  },
                onRefresh = { }
            )
        },
        floatingActionButton = {
            SmallFloatingActionButton(onClick = onAddKattamClick) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.add_kattam))
            }
        }
    ) { pv -> Kattams(pv) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Kattams(paddingValues: PaddingValues
) {
    Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Kattams")
            SvgImageFromApi(
                svgUrl = "/astro/chart?lang=ta&tz=Asia/Kolkata&dateandtime=${ZonedDateTime.now(
                    ZoneOffset.UTC)}", // <- your endpoint ,
                contentDescription = "Company logo"
            )
        }
    }
}

@Composable
@NDAstroPreview()
@OptIn(ExperimentalMaterial3Api::class)
private fun KattamsPreview() {
    NDAstroTheme {
        Surface {
            Kattams(PaddingValues.Zero)
        }
    }
}
