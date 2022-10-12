package meh.daniel.com.githubliteapp.presentation.ui.repositoryinfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.domain.SessionRepository
import meh.daniel.com.githubliteapp.presentation.base.BaseViewModel
import meh.daniel.com.githubliteapp.presentation.utils.Base64Decoder

@HiltViewModel
class DetailRepositoryViewModel @Inject constructor(
    private val repository: SessionRepository
) : BaseViewModel() {

    private val _state = MutableLiveData<DetailRepositoryState>(DetailRepositoryState.Loading)
    var state: LiveData<DetailRepositoryState> = _state

    fun loadDetailRepository(idRepo: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value is DetailRepositoryState.Loading) {
                try {
                    val repo = repository.getRepository(idRepo)
                    setState(DetailRepositoryState.Loaded(
                        githubRepo = repo,
                        readmeState = DetailRepositoryState.ReadmeState.Loading,
                    ))
                    loadReadme()
                } catch (e: Exception) {
                    setState(DetailRepositoryState.Error(e.message.toString(), true))
                }
            }
        }
    }

    private fun setState(state: DetailRepositoryState) {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = state
        }
    }

    private fun loadReadme() {
        if (_state.value is DetailRepositoryState.Loaded && (_state.value as DetailRepositoryState.Loaded).readmeState is DetailRepositoryState.ReadmeState.Loading) {
            viewModelScope.launch {
                val content = _state.value as DetailRepositoryState.Loaded
                val readme = repository.getRepositoryReadme(ownerName = "meh-daniel",
                    repositoryName = content.githubRepo.name,
                    branchName = content.githubRepo.branchName)
                if(readme.content.isEmpty()){
                    setState(DetailRepositoryState.Loaded(
                        githubRepo = content.githubRepo,
                        readmeState = DetailRepositoryState.ReadmeState.Empty
                    ))
                }else {
                    setState(DetailRepositoryState.Loaded(
                        githubRepo = content.githubRepo,
                        readmeState = DetailRepositoryState.ReadmeState.Loaded(
                            markdown = Base64Decoder.decode(readme.content)
                        )
                    ))
                }
            }
        }
    }

}