package meh.daniel.com.githubliteapp.presentation.ui

sealed class Event {
    data class ShowSnackbar(val message: String): Event()
}