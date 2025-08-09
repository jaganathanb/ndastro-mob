package dev.dapps.ndastro.auth.ui

import dev.dapps.ndastro.commons.ViewEvent
import dev.dapps.ndastro.commons.ViewState

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val usernameError: String? = null,
    val passwordError: String? = null,
    val loginError: String? = null,
) : ViewState


sealed class LoginEvent : ViewEvent {
    data class UserNameChanged(val username: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data object TogglePasswordVisibility : LoginEvent()
    data object LoginClicked : LoginEvent()
}

sealed class LoginNavigationEvents {
    data object Authenticated : LoginNavigationEvents()
}