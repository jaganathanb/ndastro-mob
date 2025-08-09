package dev.dapps.ndastro.home.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dapps.ndastro.AuthState
import dev.dapps.ndastro.auth.data.GetUserUseCase
import dev.dapps.ndastro.auth.data.LoginResponse
import dev.dapps.ndastro.auth.data.toUser
import dev.dapps.ndastro.commons.EventStateViewModel
import dev.dapps.ndastro.commons.ViewModelEvents
import dev.dapps.ndastro.data.network.NetworkResult
import dev.dapps.ndastro.home.data.HomeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val getUserUseCase: GetUserUseCase,
    viewModelEvents: ViewModelEvents<HomeNavigationEvents>,
) : EventStateViewModel<HomeState, HomeEvent>(),
    ViewModelEvents<HomeNavigationEvents> by viewModelEvents {

    override val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())

    init {
        getUserInfo()
    }

    private fun getUserInfo() = viewModelScope.launch {
        val user = getUserUseCase()
        when (user) {
            is NetworkResult.Success<LoginResponse> -> {
                updateState(_state.value.copy(user = user.data.toUser()))
            }

            else -> {}
        }
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ShowLogoutDialog -> updateState(_state.value.copy(showLogoutDialog = true))
            is HomeEvent.DismissLogoutDialog -> updateState(_state.value.copy(showLogoutDialog = false))
            is HomeEvent.ConfirmLogout -> {
                performLogout()
            }
        }
    }

    private fun performLogout() = viewModelScope.launch {
        homeUseCase.logout()
        updateState(_state.value.copy(showLogoutDialog = false))
        sendEvent(HomeNavigationEvents.LoggedOut)
    }
}
