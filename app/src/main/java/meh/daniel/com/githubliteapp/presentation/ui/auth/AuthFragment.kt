package meh.daniel.com.githubliteapp.presentation.ui.auth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
        initButtonSignIn()
    }

    override fun setupSubscribers() {
        setupSubscriberState()
        setupSubscriberAction()
        setupSubscriberEditText()
    }

    private fun initButtonSignIn() {
        binding.signInBtn.setOnClickListener {
            viewModel.setToken(binding.tokenEdTxt.text.toString())
            viewModel.onSignButtonPressed()
        }
    }

    private fun setupSubscriberEditText(){
        viewModel.token.onEach { token ->
            binding.tokenEdTxt.setText(token)
        }.observeInLifecycle(viewLifecycleOwner)
    }

    private fun setupSubscriberAction() {
        viewModel.actionFlow.onEach { action ->
            when(action) {
                is AuthAction.RouteToMain -> {
                    findNavController().navigate(R.id.action_authFragment_to_repositoriesListFragment)
                }
                is AuthAction.ShowError -> {
                    //TODO: тут нужно реализовать показ ошибки в самом editText
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

    private fun setupSubscriberState(){
        viewModel.stateFlow.onEach { state ->
            with(binding){
                signInBtn.text = if(state is AuthState.Loading) "" else getText(R.string.sign_in)
                progressBarOfButton.visibility = if(state is AuthState.Loading) View.VISIBLE else View.INVISIBLE
                signInBtn.visibility = if(state is AuthState.Loading) View.GONE else View.VISIBLE

            }
        }.observeInLifecycle(viewLifecycleOwner)
    }
}