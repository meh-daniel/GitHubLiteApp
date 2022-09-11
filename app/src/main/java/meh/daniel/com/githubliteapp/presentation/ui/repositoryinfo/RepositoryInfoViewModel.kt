package meh.daniel.com.githubliteapp.presentation.ui.repositoryinfo

import meh.daniel.com.domain.model.repository.Repository
import meh.daniel.com.githubliteapp.presentation.base.BaseViewModel

sealed interface State {
    object Loading : State
    data class Error(val error: String) : State

    data class Loaded(
        val githubRepo: Repository,
        val readmeState: ReadmeState
    ) : State
}

sealed interface ReadmeState {
    object Loading : ReadmeState
    object Empty : ReadmeState
    data class Error(val error: String) : ReadmeState
    data class Loaded(val markdown: String) : ReadmeState
}

class RepositoryInfoViewModel : BaseViewModel() {
//    val state: LiveData<State>

}