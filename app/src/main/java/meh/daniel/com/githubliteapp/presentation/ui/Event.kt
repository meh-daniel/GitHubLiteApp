package meh.daniel.com.githubliteapp.presentation.ui

sealed class Event {
    class ShowSnackbar(val message: String) : Event()
}