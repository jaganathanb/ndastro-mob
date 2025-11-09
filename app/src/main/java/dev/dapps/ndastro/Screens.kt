package dev.dapps.ndastro

sealed class MainScreens(var route: String) {
    data object Home : MainScreens("home")
    data object Settings : MainScreens("settings")
    data object Profile : MainScreens("profile")
    data object Kattam : MainScreens("kattam")
}

sealed class AuthScreens(var route: String) {
    data object Login : AuthScreens("login")
    data object Register : AuthScreens("register")
    data object ForgotPassword : AuthScreens("forgot_password")
}

sealed class RootScreens(var route: String) {
    data object Main : RootScreens("main")
    data object Auth : RootScreens("auth")
}