package meh.daniel.com.githubliteapp.presentation.ui.repositoryinfo

import meh.daniel.com.domain.model.repository.Repository


sealed interface State {
    object Loading : State
    data class Error(val error: String) : State

    data class Loaded(
        val githubRepo: Repository,
        val readmeState: ReadmeState
    ) : State
}