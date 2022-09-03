package meh.daniel.com.githubliteapp.presentation.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import meh.daniel.com.domain.model.token.ValidationResult
import meh.daniel.com.domain.repositories.SignRepository
import meh.daniel.com.githubliteapp.presentation.base.BaseViewModel
import meh.daniel.com.githubliteapp.presentation.ui.Event

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signRepository: SignRepository
) : BaseViewModel(){

    private val _token: MutableLiveData<String> = MutableLiveData()
    var token : LiveData<String> = _token

    private val _eventChanel = Channel<Event>(Channel.BUFFERED)
    var eventFlow = _eventChanel.receiveAsFlow()

    private val _actionChannel = Channel<AuthAction>()
    var actionFlow = _actionChannel.receiveAsFlow()

    private val _validationState = MutableUIStateFlow<ValidationResult>()
    val validationState = _validationState.asStateFlow()

    fun onSignButtonPressed(token: String) {
        viewModelScope.launch(Dispatchers.IO){
            val repo = signRepository.signIn(token = "Token $token")
            if (!repo.successful){
                sendEvent(Event.ShowSnackbar(repo.errorMessage!!))
                sendAction(AuthAction.ShowError(repo.errorMessage!!))
            } else{
                sendAction(AuthAction.RouteToMain)
            }
        }
    }

    fun sendEvent(event: Event){
        viewModelScope.launch(Dispatchers.IO){
            _eventChanel.send(event)
        }
    }
    private fun sendAction(action: AuthAction){
        viewModelScope.launch(Dispatchers.IO){
            _actionChannel.send(action)
        }
    }
}