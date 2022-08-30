package meh.daniel.com.githubliteapp.presentation.auth

sealed class State {
    object Idle : State()
    object Loading : State()
    data class InvalidInput(val reason: String) : State()
}