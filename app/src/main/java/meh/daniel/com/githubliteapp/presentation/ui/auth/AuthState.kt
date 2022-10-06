package meh.daniel.com.githubliteapp.presentation.ui.auth

sealed interface AuthState {
    object Idle : AuthState
    object Loading : AuthState
    data class InvalidInput(val reason: String) : AuthState
}