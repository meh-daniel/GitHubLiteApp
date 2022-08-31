package meh.daniel.com.githubliteapp.presentation.ui.auth

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentAuthBinding
import meh.daniel.com.githubliteapp.presentation.BaseFragment
import meh.daniel.com.githubliteapp.presentation.Event
import meh.daniel.com.githubliteapp.presentation.activityNavController
import meh.daniel.com.githubliteapp.presentation.ui.auth.AuthAction.RouteToMain
import meh.daniel.com.githubliteapp.presentation.ui.auth.AuthAction.ShowError
import meh.daniel.com.githubliteapp.presentation.navigateSafely

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>(R.layout.fragment_auth, R.id.authFragment){

    private val viewModel: AuthViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAuthBinding.inflate(inflater, container, false)

//    initView()
//    eventFlowLifecycle()
//    actionFlowLifecycle()

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    private fun actionFlowLifecycle() {
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.actionFlow.collect() { action ->
                    when (action) {
                        is RouteToMain -> {
                            activityNavController().navigateSafely(R.id.action_authFragment_to_repositoriesListFragment)
                        }
                        is ShowError -> viewModel.sendEvent(Event.ShowSnackbar(action.message))
                    }
                }
            }
        }
    }

    private fun initView() {
        binding.signInBtn.setOnClickListener {
            viewModel.onSignButtonPressed(binding.tokenEdTxt.text.toString())
        }

    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    private fun eventFlowLifecycle(){
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.eventFlow.collect() { event ->
                    when(event) {
                        is Event.ShowSnackbar -> {
                            Snackbar.make(binding.root, event.message, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}