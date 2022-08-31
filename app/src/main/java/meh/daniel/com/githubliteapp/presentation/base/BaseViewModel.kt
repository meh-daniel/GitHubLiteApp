package meh.daniel.com.githubliteapp.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import meh.daniel.com.githubliteapp.presentation.ui.UIState

abstract class BaseViewModel : ViewModel() {
    /**
     * Creates [MutableStateFlow] with [UIState] and the given initial value [UIState.Idle]
     */
    @Suppress("FunctionName")
    fun <T> MutableUIStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())
}