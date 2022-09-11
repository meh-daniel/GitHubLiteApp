package meh.daniel.com.githubliteapp.presentation.ui.auth

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import meh.daniel.com.domain.repositories.SignRepository
import meh.daniel.com.githubliteapp.presentation.base.BaseViewModel

sealed class State {
    object Idle : State()
    object Loading : State()
    data class InvalidInput(val reason: String) : State()
}

sealed class Action {
    data class ShowError(val message: String) : Action()
    object RouteToMain : Action()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signRepository: SignRepository
) : BaseViewModel(){

    private val _actionChannel = Channel<Action>()
    var actionFlow = _actionChannel.receiveAsFlow()

    private val _stateChannel = MutableStateFlow<State>(State.Idle)
    val stateFlow = _stateChannel.asStateFlow()

    private val _token = MutableStateFlow("")
    val token : StateFlow<String> = _token.asStateFlow()

    fun onSignButtonPressed() {
        viewModelScope.launch(Dispatchers.IO){
            _stateChannel.value = State.Loading
            delay(2000L)
            val repo = signRepository.signIn(token = "Token ${_token.value}")
            if (!repo.successful){
                sendAction(Action.ShowError(repo.errorMessage!!))
            } else{
                sendAction(Action.RouteToMain)
            }
            _stateChannel.value = State.Idle
        }
    }

    fun setToken(token: String){
        _token.value = token
    }

    fun sendAction(action: Action){
        viewModelScope.launch(Dispatchers.Main){
            _actionChannel.send(action)
        }
    }
}