package meh.daniel.com.githubliteapp.presentation.ui.auth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentAuthBinding
import meh.daniel.com.githubliteapp.presentation.base.BaseFragment
import meh.daniel.com.githubliteapp.presentation.ui.Event
import meh.daniel.com.githubliteapp.presentation.utils.observeInLifecycle

@AndroidEntryPoint
class AuthFragment : BaseFragment<AuthViewModel, FragmentAuthBinding>(R.layout.fragment_auth){

    override val viewModel: AuthViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAuthBinding.inflate(inflater, container, false)

    override fun setupListeners() {
        super.setupListeners()
        initButtonSignIn()
    }

    override fun setupSubscribers() {
        super.setupSubscribers()
        eventFlowLifecycle()
        subscribeToAction()
    }

    override fun onStop() {
        super.onStop()
    }

    private fun initButtonSignIn() {
        binding.signInBtn.setOnClickListener {
            viewModel.onSignButtonPressed(binding.tokenEdTxt.text.toString())
        }
    }

    private fun subscribeToAction() {
        viewModel.validationState.collectUIState(
            state = {

            },
            onError = {
                viewModel.sendEvent(Event.ShowSnackbar("AAAAA"))
            },
            onSuccess = {
                findNavController().navigate(R.id.action_authFragment_to_repositoriesListFragment)
            }
        )
    }

    private fun eventFlowLifecycle(){
        viewModel.eventFlow
            .onEach { event ->
                when(event) {
                    is Event.ShowSnackbar -> {
                        Snackbar.make(binding.root, event.message, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
            .observeInLifecycle(viewLifecycleOwner)
    }
}