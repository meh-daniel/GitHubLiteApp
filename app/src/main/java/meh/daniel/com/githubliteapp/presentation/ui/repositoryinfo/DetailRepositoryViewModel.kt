package meh.daniel.com.githubliteapp.presentation.ui.repositoryinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.domain.SessionRepository
import meh.daniel.com.githubliteapp.presentation.base.BaseViewModel

class DetailRepositoryViewModel @Inject constructor(
    private val repository: SessionRepository
) : BaseViewModel() {

    private val _state = MutableLiveData<DetailRepositoryState>(DetailRepositoryState.Loading)
    var state: LiveData<DetailRepositoryState> = _state

    private fun loadDetailRepository(idRepo: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value is DetailRepositoryState.Loading) {
                viewModelScope.launch {
                    _state.value = DetailRepositoryState.Loading
                    try {
                        val repo = repository.getRepository(idRepo)
                        _state.value = DetailRepositoryState.Loaded(
                            githubRepo = repo,
                            readmeState = DetailRepositoryState.ReadmeState.Loading,
                        )
//                        loadReadme()
                    } catch (e: Exception) {
                        _state.value = DetailRepositoryState.Error(e.message.toString(), true)
                    }
                }
            }
        }
    }

//    private fun loadReadme() {
//        if (_state.value is DetailRepositoryState.Loaded &&
//            (_state.value as DetailRepositoryState.Loaded).readmeState is DetailRepositoryState.ReadmeState.Loading) {
//            viewModelScope.launch {
//                val content = _state.value as DetailRepositoryState.Loaded
//                try {
//                    val readme = repository.getRepositoryReadme(ownerName = content.githubRepo.repositoryName, repositoryName = content.git)
//                    _state.value = DetailRepositoryState.Loaded(
//                        githubRepo = content.githubRepo,
//                        readmeState = DetailRepositoryState.ReadmeState.Loaded(
//                            markdown = Base64Decoder.decode(readme.content)
//                        ),
//                    )
//                } catch (e: Exception) {
//                    DetailRepositoryState.Loaded(
//                        githubRepo = content.githubRepo,
//                        readmeState = DetailRepositoryState.ReadmeState.Error(e.message.toString(), false),
//                    )
//                }
//            }
//        }
//    }

}