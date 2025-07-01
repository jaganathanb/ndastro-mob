package com.dapps.ndastro.ui.screens

sealed class Navigations {

    // Unauthenticated Routes
    sealed class Unauthenticated(val route: String) : Navigations() {
        data object NavigationRoute : Unauthenticated(route = "unauthenticated")
        data object Login : Unauthenticated(route = "login")
        data object Registration : Unauthenticated(route = "registration")
    }

    // Authenticated Routes
    sealed class Authenticated(val route: String) : Navigations() {
        data object NavigationRoute : Authenticated(route = "authenticated")
        data object Dashboard : Authenticated(route = "Dashboard")
    }
}