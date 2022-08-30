package meh.daniel.com.githubliteapp.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import meh.daniel.com.githubliteapp.domain.model.token.KeyValueStorage
import meh.daniel.com.githubliteapp.presentation.Event

@HiltViewModel
class AuthViewModel : ViewModel(){

    private val _token: MutableLiveData<String> = MutableLiveData()
    var token : LiveData<String> = _token

    private val _eventChanel = Channel<Event>()
    var eventFlow = _eventChanel.receiveAsFlow()

//    val state: LiveData<State>
//    val actions: Flow<Action>

    fun onSignButtonPressed() {
        // TODO:
    }
}