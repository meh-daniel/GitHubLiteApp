package meh.daniel.com.githubliteapp.presentation.ui.auth

sealed interface AuthAction {
    data class ShowError(val message: String) : AuthAction
    object RouteToMain : AuthAction
}