package meh.daniel.com.githubliteapp.presentation.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.domain.SessionRepository
import meh.daniel.com.githubliteapp.presentation.base.BaseViewModel

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: SessionRepository
): BaseViewModel() {

    interface Action {
        object routeToAuth: Action
        object routeToRepositoryList: Action
    }

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    init {
        checkOfSign()
    }

    private fun checkOfSign() {
        viewModelScope.launch(Dispatchers.IO) {
            val check = repository.checkRegister()
            if (check) setAction(Action.routeToRepositoryList) else setAction(Action.routeToAuth)
        }
    }

    private fun setAction(state: Action) {
        viewModelScope.launch(Dispatchers.Main) {
            _action.value = state
        }
    }

}