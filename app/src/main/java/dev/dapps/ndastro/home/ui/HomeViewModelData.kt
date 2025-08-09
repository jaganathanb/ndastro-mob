package dev.dapps.ndastro.home.ui

import dev.dapps.ndastro.auth.data.User
import dev.dapps.ndastro.commons.ViewEvent
import dev.dapps.ndastro.commons.ViewState

sealed class HomeEvent : ViewEvent {
    object ShowLogoutDialog : HomeEvent()
    object ConfirmLogout : HomeEvent()
    object DismissLogoutDialog : HomeEvent()
}

data class HomeState(
    val showLogoutDialog: Boolean = false,
    val user: User? = null,
) : ViewState

sealed class HomeNavigationEvents {
    data object LoggedOut : HomeNavigationEvents()
}