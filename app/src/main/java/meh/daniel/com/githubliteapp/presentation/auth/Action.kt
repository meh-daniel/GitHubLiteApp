package meh.daniel.com.githubliteapp.presentation.auth

sealed class Action {
    data class ShowError(val message: String) : Action()
    object RouteToMain : Action()
}