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

    sealed interface State {
        object Idle : State
        object Loading : State
        data class InvalidInput(val reason: String) : State
    }

    sealed interface Action {
        data class ShowError(val message: String) : Action
        object RouteToMain : Action
    }

    private val _action: Channel<Action> = Channel(Channel.BUFFERED)
    var actionFlow: Flow<Action> = _action.receiveAsFlow()

    private val _state = MutableStateFlow<State>(State.Idle)
    val stateFlow: Flow<State> = _state.asStateFlow()

    private val _token = MutableStateFlow("")
    val token : StateFlow<String> = _token.asStateFlow()

    fun onSignButtonPressed() {
        viewModelScope.launch(Dispatchers.IO){
            _state.value = State.Loading
            delay(2000L)
            val repo = signRepository.signIn(token = "Token ${_token.value}")
            if (!repo.successful){
                sendAction(Action.ShowError(repo.errorMessage!!))
                delay(7000L)
                _state.value = State.Idle
            } else{
                sendAction(Action.RouteToMain)
            }
            _state.value = State.Idle
        }
    }

    fun showError(message: String) {
        _state.value = State.InvalidInput(reason = message)
    }

    fun setToken(token: String){
        _token.value = token
    }

    private fun sendAction(action: Action){
        viewModelScope.launch(Dispatchers.Main){
            _action.send(action)
        }
    }

}