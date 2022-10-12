package meh.daniel.com.githubliteapp.presentation.ui.repositorieslist

import meh.daniel.com.domain.model.Repo

sealed interface RepositoriesListState {
    object Loading : RepositoriesListState
    data class Loaded(val repos: List<Repo>) : RepositoriesListState
    data class Error(val error: String, val isNoConnectionError: Boolean) : RepositoriesListState
    object Empty : RepositoriesListState
}