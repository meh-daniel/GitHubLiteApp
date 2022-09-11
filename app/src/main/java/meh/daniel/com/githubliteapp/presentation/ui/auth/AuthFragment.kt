package meh.daniel.com.githubliteapp.presentation.ui.auth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentAuthBinding
import meh.daniel.com.githubliteapp.presentation.base.BaseFragment
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

    private fun initButtonSignIn() {
        binding.signInBtn.setOnClickListener {
            viewModel.onSignButtonPressed(binding.tokenEdTxt.text.toString())
        }
    }

    private fun subscribeToAction() {

    }

    private fun startLoading(){
        with(binding){
            signInBtn.text = ""
            progressBarOfButton.visibility = View.VISIBLE
        }
    }

    private fun stopLoading(){
        with(binding){
            signInBtn.text = getText(R.string.sign_in)
            progressBarOfButton.visibility = View.INVISIBLE
        }
    }

    private fun eventFlowLifecycle(){
        viewModel.stateFlow
            .onEach { state ->
                when(state) {
                    is State.Idle -> {
                        startLoading()
                    }
                    is State.Loading -> {
                        stopLoading()
                    }
                    is State.InvalidInput -> {
                        stopLoading()
                        viewModel.sendAction(Action.ShowError(state.reason))
                    }
                }
            }
            .observeInLifecycle(viewLifecycleOwner)
        viewModel.actionFlow
            .onEach { action ->
                when(action) {
                    is Action.RouteToMain -> {
                        findNavController().navigate(R.id.action_authFragment_to_repositoriesListFragment)
                    }
                    is Action.ShowError -> {
                        Snackbar.make(binding.root, action.message, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
            .observeInLifecycle(viewLifecycleOwner)
    }
}