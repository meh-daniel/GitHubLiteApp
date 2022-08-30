package meh.daniel.com.githubliteapp.presentation.auth

sealed class AuthAction {
    data class ShowError(val message: String) : AuthAction()
    object RouteToMain : AuthAction()
}