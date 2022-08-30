package meh.daniel.com.githubliteapp.presentation.auth

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class InvalidInput(val reason: String) : AuthState()
}