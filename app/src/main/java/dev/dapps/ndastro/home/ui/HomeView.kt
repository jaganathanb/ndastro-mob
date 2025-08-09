package dev.dapps.ndastro.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.dapps.ndastro.R
import dev.dapps.ndastro.commons.ViewEvent
import dev.dapps.ndastro.ui.theme.NDAstroPreview
import dev.dapps.ndastro.ui.theme.NDAstroTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel(),
    onLoggedOut: () -> Unit = {},
) {
    val state by viewModel.state.collectAsState()
    Home(state = state, onEvent = { viewModel.onEvent(it) })
    ViewEvent(viewModel.eventsFlow) { event ->
        when (event) {
            HomeNavigationEvents.LoggedOut -> onLoggedOut()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    state: HomeState = HomeState(),
    onEvent: (HomeEvent) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.home), fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { onEvent.invoke(HomeEvent.ShowLogoutDialog) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ExitToApp,
                            contentDescription = stringResource(R.string.logout),
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = String.format(stringResource(R.string.welcome), "${state.user?.name}"),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Navigate to Profile */ },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            ) {
                Text(text = stringResource(R.string.go_to_profile), fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Navigate to Settings */ },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
            ) {
                Text(text = "Settings", fontSize = 16.sp, color = Color.White)
            }
        }
    }

    // Show logout confirmation dialog
    if (state.showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { onEvent(HomeEvent.DismissLogoutDialog) },
            title = { Text(stringResource(R.string.logout)) },
            text = { Text(stringResource(R.string.log_out_confirmation)) },
            confirmButton = {
                TextButton(onClick = { onEvent(HomeEvent.ConfirmLogout) }) {
                    Text(stringResource(R.string.yes), color = MaterialTheme.colorScheme.primary)
                }
            },
            dismissButton = {
                TextButton(onClick = { onEvent(HomeEvent.DismissLogoutDialog) }) {
                    Text(stringResource(R.string.cancel), color = MaterialTheme.colorScheme.secondary)
                }
            },
        )
    }
}

@Composable
@NDAstroPreview
private fun HomePreview() {
    NDAstroTheme {
        Surface {
            Home()
        }
    }
}
