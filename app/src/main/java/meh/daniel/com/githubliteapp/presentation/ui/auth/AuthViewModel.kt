package meh.daniel.com.githubliteapp.presentation.ui.auth

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
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
    var actionFlow = _action.receiveAsFlow()

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val stateFlow= _state.asStateFlow()

    fun onSignButtonPressed(token: String) {
        viewModelScope.launch(Dispatchers.IO){
            if(!validateToken(token)){
                setState(AuthState.InvalidInput("invalid token"))
            } else {
                setState(AuthState.Loading)
                try {
                    val tokenSuccessful = signRepository.signIn(token = token)
                    if (tokenSuccessful.successful) {
                        sendAction(AuthAction.RouteToMain)
                    } else {
                        setState(AuthState.InvalidInput("invalid token"))
                    }
                } catch (e: Throwable) {
                    sendAction(AuthAction.ShowError(e.message.toString()))
                }
            }
        }
    }

    private fun validateToken(token: String) : Boolean{
        return token.isNotEmpty()
    }

    fun onTextChanged() {
        viewModelScope.launch(Dispatchers.Main) {
            if (_state.value is AuthState.InvalidInput) _state.value = AuthState.Idle
        }
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