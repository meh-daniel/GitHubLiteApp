package meh.daniel.com.githubliteapp.presentation.ui.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentSplashBinding
import meh.daniel.com.githubliteapp.presentation.base.BaseFragment

@AndroidEntryPoint
class SplashFragment: BaseFragment<SplashViewModel, FragmentSplashBinding>(R.layout.fragment_splash) {

    override val viewModel: SplashViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSplashBinding.inflate(inflater, container, false)

    override fun setupSubscribers() {
        observe()
    }

    private fun observe() {
        viewModel.action.observe(this) { action ->
            when(action) {
                is SplashViewModel.Action.routeToAuth -> {
                    findNavController().navigate(R.id.action_splashFragment_to_authFragment)
                }
                is SplashViewModel.Action.routeToRepositoryList -> {
                    findNavController().navigate(R.id.action_splashFragment_to_repositoriesListFragment)
                }
            }
        }
    }

}