package meh.daniel.com.githubliteapp.presentation.repositoryinfo

import meh.daniel.com.githubliteapp.domain.model.Repository

class RepositoryInfoViewModel {
//    val state: LiveData<State>

    sealed interface State {
        object Loading : State
        data class Error(val error: String) : State

        data class Loaded(
            val githubRepository: Repository,
            val readmeState: ReadmeState
        ) : State
    }

    sealed interface ReadmeState {
        object Loading : ReadmeState
        object Empty : ReadmeState
        data class Error(val error: String) : ReadmeState
        data class Loaded(val markdown: String) : ReadmeState
    }
}