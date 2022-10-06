package meh.daniel.com.githubliteapp.presentation.ui.repositorieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.domain.SessionRepository
import meh.daniel.com.domain.model.Repo
import meh.daniel.com.githubliteapp.presentation.base.BaseViewModel

@HiltViewModel
class RepositoriesListViewModel @Inject constructor(
    private val repository: SessionRepository
) : BaseViewModel() {

    private val _state = MutableLiveData<RepositoriesListState>(RepositoriesListState.Loading)
    var state: LiveData<RepositoriesListState> = _state

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value is RepositoriesListState.Loading){
                setState(RepositoriesListState.Loaded(repository.getRepositories()))
            }
        }
    }

    private fun setState(state: RepositoriesListState) {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = state
        }
    }


}