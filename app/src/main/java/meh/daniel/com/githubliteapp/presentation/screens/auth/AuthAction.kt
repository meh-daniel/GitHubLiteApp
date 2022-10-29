package meh.daniel.com.githubliteapp.presentation.screens.auth

sealed interface AuthAction {
    data class ShowError(val message: String) : AuthAction
    object RouteToMain : AuthAction
}