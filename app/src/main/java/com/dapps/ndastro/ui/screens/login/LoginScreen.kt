package com.dapps.ndastro.ui.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.size.Scale
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.request.crossfade
import com.dapps.ndastro.R
import com.dapps.ndastro.ui.common.state.customComposables.MediumTitleText
import com.dapps.ndastro.ui.common.state.customComposables.TitleText
import com.dapps.ndastro.ui.screens.login.state.LoginUiEvent
import com.dapps.ndastro.ui.theme.AppTheme
import com.dapps.ndastro.ui.theme.NDAstroTheme

@Composable
fun LoginView(
    loginViewModel: LoginViewModel = viewModel(),
    onNavigateToRegistration: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {

    val loginState by remember {
        loginViewModel.loginState
    }

    if (loginState.isLoginSuccessful) {
        /**
         * Navigate to Authenticated navigation route
         * once login is successful
         */
        LaunchedEffect(key1 = true) {
            onNavigateToAuthenticatedRoute.invoke()
        }
    } else {

        // Full Screen Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Main card Content for Login
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimens.paddingLarge)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = AppTheme.dimens.paddingLarge)
                        .padding(bottom = AppTheme.dimens.paddingExtraLarge)
                ) {

                    // Heading Jetpack Compose
                    MediumTitleText(
                        modifier = Modifier
                            .padding(top = AppTheme.dimens.paddingLarge)
                            .fillMaxWidth(),
                        text = stringResource(id = R.string.nd_astro),
                        textAlign = TextAlign.Center
                    )

                    // Login Logo
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(128.dp)
                            .padding(top = AppTheme.dimens.paddingSmall),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(data = R.drawable.dapps)
                            .crossfade(enable = true)
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = stringResource(id = R.string.login_title)
                    )

                    // Heading Login
                    TitleText(
                        modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                        text = stringResource(id = R.string.login_title)
                    )

                    // Login Inputs Composable
                    LoginInputs(
                        loginState = loginState,
                        onEmailOrMobileChange = { inputString ->
                            loginViewModel.onUiEvent(
                                loginUiEvent = LoginUiEvent.EmailOrMobileChanged(
                                    inputString
                                )
                            )
                        },
                        onPasswordChange = { inputString ->
                            loginViewModel.onUiEvent(
                                loginUiEvent = LoginUiEvent.PasswordChanged(
                                    inputString
                                )
                            )
                        },
                        onSubmit = {
                            loginViewModel.onUiEvent(loginUiEvent = LoginUiEvent.Submit)
                        },
                        onForgotPasswordClick = onNavigateToForgotPassword
                    )

                }
            }

            // Register Section
            Row(
                modifier = Modifier.padding(AppTheme.dimens.paddingNormal),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Don't have an account?
                Text(text = stringResource(id = R.string.no_account))

                //Register
                Text(
                    modifier = Modifier
                        .padding(start = AppTheme.dimens.paddingExtraSmall)
                        .clickable {
                            onNavigateToRegistration.invoke()
                        },
                    text = stringResource(id = R.string.register),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    NDAstroTheme {
        LoginView (
            onNavigateToForgotPassword = {},
            onNavigateToRegistration = {},
            onNavigateToAuthenticatedRoute = {}
        )
    }
}