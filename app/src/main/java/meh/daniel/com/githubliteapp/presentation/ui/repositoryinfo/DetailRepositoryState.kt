package meh.daniel.com.githubliteapp.presentation.ui.repositoryinfo

import meh.daniel.com.domain.model.repository.RepoDetails

interface DetailRepositoryState {

    object Loading : DetailRepositoryState
    data class Error(val error: String, val isNoConnectionError: Boolean) : DetailRepositoryState
    data class Loaded(
        val githubRepo: RepoDetails,
        val readmeState: ReadmeState,
    ) : DetailRepositoryState

    sealed interface ReadmeState {
        object Loading : ReadmeState
        object Empty : ReadmeState
        data class Error(val error: String, val isNoConnectionError: Boolean) : ReadmeState
        data class Loaded(val markdown: String) : ReadmeState
    }

}