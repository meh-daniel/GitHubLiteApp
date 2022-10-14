package meh.daniel.com.githubliteapp.presentation.ui.repositoryinfo

import meh.daniel.com.domain.model.RepoDetails

interface DetailRepositoryState {

    object Loading : DetailRepositoryState
    data class Error(val error: String, val isNoConnectionError: Boolean) : DetailRepositoryState
    data class Loaded(
        val githubRepo: RepoDetails,
        val readme: String,
    ) : DetailRepositoryState

}