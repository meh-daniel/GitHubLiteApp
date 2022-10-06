package meh.daniel.com.githubliteapp.presentation.ui.auth

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import meh.daniel.com.domain.SessionRepository
import meh.daniel.com.githubliteapp.presentation.base.BaseViewModel

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signRepository: SessionRepository
) : BaseViewModel(){

    private val _action: Channel<AuthAction> = Channel(Channel.BUFFERED)
    var actionFlow: Flow<AuthAction> = _action.receiveAsFlow()

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val stateFlow: Flow<AuthState> = _state.asStateFlow()

    private val _token = MutableStateFlow("")
    val token : StateFlow<String> = _token.asStateFlow()

    fun onSignButtonPressed() {
        viewModelScope.launch(Dispatchers.IO){
            setState(AuthState.Loading)
            val repo = signRepository.signIn(token = _token.value)
            if (!repo.successful){
                sendAction(AuthAction.ShowError(repo.errorMessage!!))
                setState(AuthState.Idle)
            } else{
                sendAction(AuthAction.RouteToMain)
            }
            setState(AuthState.Idle)
        }
    }

    fun setToken(token: String){
        _token.value = token
    }

    private fun setState(state: AuthState) {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = state
        }
    }

    private fun sendAction(action: AuthAction){
        viewModelScope.launch(Dispatchers.Main){
            _action.send(action)
        }
    }

}