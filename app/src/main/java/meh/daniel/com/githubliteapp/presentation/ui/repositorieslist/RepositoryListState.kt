package meh.daniel.com.githubliteapp.presentation.ui.repositorieslist

import meh.daniel.com.githubliteapp.domain.model.repository.Repository

sealed interface State {
    object Loading : State
    data class Loaded(val repos: List<Repository>) : State
    data class Error(val error: String) : State
    object Empty : State
}