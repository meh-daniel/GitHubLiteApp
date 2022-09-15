package meh.daniel.com.githubliteapp.presentation.ui.repositorieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.domain.repositories.SessionRepository
import meh.daniel.com.domain.model.repository.Repo
import meh.daniel.com.githubliteapp.presentation.base.BaseViewModel

@HiltViewModel
class RepositoriesListViewModel @Inject constructor(
    private val repository: SessionRepository
) : BaseViewModel() {

    private val _repositories: MutableLiveData<List<Repo>> = MutableLiveData()
    var repositories: LiveData<List<Repo>> = _repositories

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val repositories = repository.getRepositories()
                _repositories.postValue(repositories)
            } catch (e : Throwable) {

            }
        }
    }

    sealed interface State {
        object Loading : State
        data class Loaded(val repos: List<Repo>) : State
        data class Error(val error: String) : State
        object Empty : State
    }

}