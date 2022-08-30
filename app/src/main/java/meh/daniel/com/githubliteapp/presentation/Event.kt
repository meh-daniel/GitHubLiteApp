package meh.daniel.com.githubliteapp.presentation

sealed class Event {
    data class ShowSnackbar(val message: String): Event()
}