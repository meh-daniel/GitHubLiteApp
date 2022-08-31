package meh.daniel.com.githubliteapp.presentation.ui.auth

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentAuthBinding
import meh.daniel.com.githubliteapp.presentation.base.BaseFragment
import meh.daniel.com.githubliteapp.presentation.ui.Event

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

    private fun initButtonSignIn() {
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