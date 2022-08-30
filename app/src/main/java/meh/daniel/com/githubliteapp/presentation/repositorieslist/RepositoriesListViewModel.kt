package meh.daniel.com.githubliteapp.presentation.repositorieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.githubliteapp.domain.AppRepository
import meh.daniel.com.githubliteapp.domain.model.repository.Repository

@HiltViewModel
class RepositoriesListViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _repositories: MutableLiveData<List<Repository>> = MutableLiveData()
    var repositories: LiveData<List<Repository>> = _repositories

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val repositories = repository.getRepositories("Token 127965625f147d17c24bd1c9f87d019324873042")
//                _repositories.postValue(repositories)
//            } catch (e : Throwable) {
//                Log.e("xxx", "error Load")
//            }
        }
    }

}