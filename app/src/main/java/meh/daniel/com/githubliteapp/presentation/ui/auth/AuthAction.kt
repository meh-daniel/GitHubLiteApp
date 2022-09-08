package meh.daniel.com.githubliteapp.presentation.ui.auth

sealed class AuthAction {
    data class ShowError(val message: String) : AuthAction()
    object RouteToMain : AuthAction()
}