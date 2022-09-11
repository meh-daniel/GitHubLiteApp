package meh.daniel.com.githubliteapp.presentation.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import meh.daniel.com.domain.repositories.SignRepository
import meh.daniel.com.githubliteapp.presentation.base.BaseViewModel
import meh.daniel.com.githubliteapp.presentation.utils.Event

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

    private val _token: MutableStateFlow<String>()
    var token : LiveData<String> = _token

    private val _actionChannel = Channel<Action>()
    var actionFlow = _actionChannel.receiveAsFlow()

    private val _stateChannel = MutableStateFlow<State>(State.Idle)
    val stateFlow = _stateChannel.asStateFlow()

    fun onSignButtonPressed(token: String) {
        viewModelScope.launch(Dispatchers.IO){
            val repo = signRepository.signIn(token = "Token $token")
            if (!repo.successful){
                sendAction(Action.ShowError(repo.errorMessage!!))
            } else{
                sendAction(Action.RouteToMain)
            }
        }
    }
    fun sendAction(action: Action){
        viewModelScope.launch(Dispatchers.Main){
            _actionChannel.send(action)
        }
    }
}