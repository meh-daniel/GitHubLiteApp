package meh.daniel.com.githubliteapp.presentation.screens.repositorieslist

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.ConnectException
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import meh.daniel.com.domain.SessionRepository
import meh.daniel.com.githubliteapp.presentation.base.BaseViewModel

@HiltViewModel
class RepositoriesListViewModel @Inject constructor(
    private val repository: SessionRepository
) : BaseViewModel() {

    private val _state = MutableStateFlow<RepositoriesListState>(RepositoriesListState.Loading)
    var state = _state.asStateFlow()

    init {
        loadRepositories()
    }

    fun loadRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            setState(RepositoriesListState.Loading)
            try {
                val repos = repository.getRepos()
                if(repos.isEmpty()) {
                    setState(RepositoriesListState.Empty)
                } else {
                    setState(RepositoriesListState.Loaded(repos))
                }
            } catch (e: Throwable) {
                when (e) {
                    is ConnectException -> setState(RepositoriesListState.Error(e.message.toString(), true))
                    else -> setState(RepositoriesListState.Error(e.message.toString(), false))
                }
            }
        }
    }

    private fun setState(state: RepositoriesListState) {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = state
        }
    }

    fun logout(){
        viewModelScope.launch(Dispatchers.Main) {
            repository.exitSession()
        }
    }

}