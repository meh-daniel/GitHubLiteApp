package meh.daniel.com.githubliteapp.presentation.screens.auth

import android.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
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
        observeEditText()
    }

    override fun setupSubscribers() {
        setupSubscriberState()
        setupSubscriberAction()
    }

    private fun initButtonSignIn() {
        binding.buttonSignIn.setOnClickListener {
            viewModel.onSignButtonPressed(binding.tokenEdTxt.text.toString())
        }
    }

    private fun observeEditText() {
        binding.tokenEdTxt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.onTextChanged()
            }

            override fun afterTextChanged(p0: Editable?) = Unit
        })
    }

    private fun setupSubscriberAction() {
        viewModel.actionFlow.onEach { action ->
            when(action) {
                is AuthAction.RouteToMain -> {
                    findNavController().navigate(R.id.action_authFragment_to_repositoriesListFragment)
                }
                is AuthAction.ShowError -> {
                    context?.let {
                        AlertDialog
                            .Builder(it)
                            .setTitle("Error")
                            .setMessage(action.message)
                            .setNegativeButton("Ok") { _, _ -> }
                            .show()
                    }
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

    private fun setupSubscriberState(){
        viewModel.stateFlow.onEach { state ->
            with(binding){
                textSignIn.visibility = if(state is AuthState.Loading) View.INVISIBLE else View.VISIBLE
                progressBarOfButton.visibility = if(state is AuthState.Loading) View.VISIBLE else View.INVISIBLE
                textInputLayout.error = if(state is AuthState.InvalidInput) state.reason else ""
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

}