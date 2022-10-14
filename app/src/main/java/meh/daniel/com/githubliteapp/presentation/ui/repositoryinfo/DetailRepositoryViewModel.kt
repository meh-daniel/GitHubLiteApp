package meh.daniel.com.githubliteapp.presentation.ui.repositoryinfo

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import meh.daniel.com.domain.SessionRepository
import meh.daniel.com.githubliteapp.presentation.base.BaseViewModel
import meh.daniel.com.githubliteapp.presentation.utils.Base64Decoder

@HiltViewModel
class DetailRepositoryViewModel @Inject constructor(
    private val repository: SessionRepository
) : BaseViewModel() {

    private val _state = MutableStateFlow<DetailRepositoryState>(DetailRepositoryState.Loading)
    var state = _state.asStateFlow()

    fun loadDetailRepository(idRepo: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value is DetailRepositoryState.Loading) {
                val repo = repository.getRepository(idRepo)
                try {
                    val readme = repository.getRepositoryReadme(
                        repositoryName = repo.name,
                        branchName = repo.branchName)
                    setState(DetailRepositoryState.Loaded(
                        githubRepo = repo,
                        readme = Base64Decoder.decode(readme.content)
                    ))
                } catch (e: Exception) {
                    setState(DetailRepositoryState.Loaded(
                        githubRepo = repo,
                        readme = "No README.md"
                    ))
                }
            }
        }
    }

    private fun setState(state: DetailRepositoryState) {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = state
        }
    }

}