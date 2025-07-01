package com.dapps.ndastro.ui.common.state

import androidx.annotation.StringRes
import com.dapps.ndastro.R


/**
 * Error state holding values for error ui
 */
data class ErrorState(
    val hasError: Boolean = false,
    @param:StringRes val errorMessageStringResource: Int = R.string.empty
)