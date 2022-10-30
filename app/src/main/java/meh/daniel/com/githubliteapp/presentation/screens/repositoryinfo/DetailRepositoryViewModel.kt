package meh.daniel.com.githubliteapp.presentation.screens.repositoryinfo

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
                try {
                    val repo = repository.getRepo(idRepo)
                    val readme = repository.getRepoReadme(
                        repositoryName = repo.name,
                        branchName = repo.branchName
                    )
                    setState(DetailRepositoryState.Loaded(
                        githubRepo = repo,
                        readme = if(readme.content == "") "No README.md" else Base64Decoder.decode(readme.content)
                    ))
                } catch (e: Throwable) {
                    when (e) {
                        is ConnectException -> setState(DetailRepositoryState.Error(e.message.toString(), true))
                        else -> setState(DetailRepositoryState.Error(e.message.toString(), false))
                    }
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