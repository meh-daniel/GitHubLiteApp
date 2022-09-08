package meh.daniel.com.githubliteapp.presentation.utils

sealed class Event {
    class ShowSnackbar(val message: String) : Event()
}