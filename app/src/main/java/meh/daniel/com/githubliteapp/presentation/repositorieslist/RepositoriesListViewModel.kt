package meh.daniel.com.githubliteapp.presentation.repositorieslist

import meh.daniel.com.githubliteapp.domain.model.Repository

class RepositoriesListViewModel {

//    val state: LiveData<State>

    sealed interface State {
        object Loading : State
        data class Loaded(val repositories: List<Repository>) : State
        data class Error(val error: String) : State
        object Empty : State
    }

}