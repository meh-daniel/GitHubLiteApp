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

    private val _splashAction = MutableLiveData<SplashAction>()
    val splashAction: LiveData<SplashAction> = _splashAction

    init {
        checkOfSign()
    }

    private fun checkOfSign() {
        viewModelScope.launch(Dispatchers.IO) {
//            if (repository.checkRegister().successful) setAction(SplashAction.RouteToAuth) else setAction(SplashAction.RouteToRepositoryList)
            setAction(SplashAction.RouteToAuth)
        }
    }

    private fun setAction(state: SplashAction) {
        viewModelScope.launch(Dispatchers.Main) {
            _splashAction.value = state
        }
    }

}