package com.dapps.ndastro.ui.screens.login.state

import com.dapps.ndastro.R
import com.dapps.ndastro.ui.common.state.ErrorState

val emailOrMobileEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.empty_user
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.empty_password
)