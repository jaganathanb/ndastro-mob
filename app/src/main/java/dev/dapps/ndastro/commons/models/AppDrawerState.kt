package dev.dapps.ndastro.commons.models

enum class AppDrawerState {
    Opened,
    Closed
}

fun AppDrawerState.isOpened(): Boolean {
    return this.name == "Opened"
}

fun AppDrawerState.opposite(): AppDrawerState {
    return if (this == AppDrawerState.Opened) AppDrawerState.Closed
    else AppDrawerState.Opened
}