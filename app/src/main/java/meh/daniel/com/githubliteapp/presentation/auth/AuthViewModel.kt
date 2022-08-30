package meh.daniel.com.githubliteapp.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import meh.daniel.com.githubliteapp.domain.TokenRepository
import meh.daniel.com.githubliteapp.presentation.Event

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val tokenRepository: TokenRepository
) : ViewModel(){

    private val _token: MutableLiveData<String> = MutableLiveData()
    var token : LiveData<String> = _token

    private val _eventChanel = Channel<Event>()
    var eventFlow = _eventChanel.receiveAsFlow()

    private val _eventAction = Channel<AuthAction>()
    var actionFlow = _eventAction.receiveAsFlow()

    //    val state: LiveData<State>

    fun onSignButtonPressed(token: String) {
        viewModelScope.launch(Dispatchers.IO){
            val repo = tokenRepository.signIn(token = "Token $token")
            if (!repo.successful){
                sendEvent(Event.ShowSnackbar(repo.errorMessage!!))
                sendAction(AuthAction.ShowError(repo.errorMessage))
            } else{
                sendAction(AuthAction.RouteToMain)
            }
        }
    }
    private fun sendEvent(event: Event){
        viewModelScope.launch(Dispatchers.IO){
            _eventChanel.send(event)
        }
    }
    private fun sendAction(action: AuthAction){
        viewModelScope.launch(Dispatchers.IO){
            _eventAction.send(action)
        }
    }
}