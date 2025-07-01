package com.dapps.ndastro.ui.screens.registration


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dapps.ndastro.R
import com.dapps.ndastro.ui.common.state.customComposables.SmallClickableWithIconAndText
import com.dapps.ndastro.ui.common.state.customComposables.TitleText
import com.dapps.ndastro.ui.screens.registration.state.RegistrationUiEvent
import com.dapps.ndastro.ui.theme.AppTheme
import com.dapps.ndastro.ui.theme.NDAstroTheme

@Composable
fun RegistrationView(
    registrationViewModel: RegistrationViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {

    val registrationState by remember {
        registrationViewModel.registrationState
    }

    if (registrationState.isRegistrationSuccessful) {
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
                .verticalScroll(rememberScrollState())
        ) {

            // Back Button Icon
            SmallClickableWithIconAndText(
                modifier = Modifier
                    .padding(horizontal = AppTheme.dimens.paddingLarge)
                    .padding(top = AppTheme.dimens.paddingLarge),
                iconContentDescription = stringResource(id = R.string.back_login),
                iconVector = Icons.AutoMirrored.Outlined.ArrowBack,
                text = stringResource(id = R.string.back_login),
                onClick = onNavigateBack
            )


            // Main card Content for Registration
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

                    // Heading Registration
                    TitleText(
                        modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                        text = stringResource(id = R.string.registration_title)
                    )

                    /**
                     * Registration Inputs Composable
                     */
                    RegistrationInputs(
                        registrationState = registrationState,
                        onEmailIdChange = { inputString ->
                            registrationViewModel.onUiEvent(
                                registrationUiEvent = RegistrationUiEvent.EmailChanged(
                                    inputValue = inputString
                                )
                            )
                        },
                        onMobileNumberChange = { inputString ->
                            registrationViewModel.onUiEvent(
                                registrationUiEvent = RegistrationUiEvent.MobileNumberChanged(
                                    inputValue = inputString
                                )
                            )
                        },
                        onPasswordChange = { inputString ->
                            registrationViewModel.onUiEvent(
                                registrationUiEvent = RegistrationUiEvent.PasswordChanged(
                                    inputValue = inputString
                                )
                            )
                        },
                        onConfirmPasswordChange = { inputString ->
                            registrationViewModel.onUiEvent(
                                registrationUiEvent = RegistrationUiEvent.ConfirmPasswordChanged(
                                    inputValue = inputString
                                )
                            )
                        },
                        onSubmit = {
                            registrationViewModel.onUiEvent(registrationUiEvent = RegistrationUiEvent.Submit)
                        }
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun RegistrationViewPreview() {
    NDAstroTheme {
        RegistrationView(onNavigateBack = {}, onNavigateToAuthenticatedRoute = {})
    }
}