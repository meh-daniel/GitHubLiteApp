package meh.daniel.com.githubliteapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
import meh.daniel.com.githubliteapp.presentation.Event
import meh.daniel.com.githubliteapp.presentation.auth.AuthAction.*

@AndroidEntryPoint
class AuthFragment : Fragment(R.layout.fragment_auth){

    private lateinit var binding: FragmentAuthBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtonSignIn()
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.actionFlow.collect() { action ->
                    when(action) {
                        is RouteToMain -> {
                            findNavController().navigate(R.id.action_authFragment_to_repositoriesListFragment)
                        }
                        is ShowError -> eventFlowLifecycle()
                    }
                }
            }
        }
    }

    private fun initButtonSignIn() {
        binding.signInBtn.setOnClickListener {
            viewModel.onSignButtonPressed(binding.tokenEdTxt.text.toString())
        }
    }

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