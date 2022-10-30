package meh.daniel.com.githubliteapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<ViewModel: BaseViewModel, Binding : ViewBinding>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId){

    private var _viewBinding: Binding ?= null

    protected val binding get() = checkNotNull(_viewBinding)
    protected abstract val viewModel: ViewModel

    protected  abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?) : Binding

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = initBinding(inflater, container)
        return binding.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        setupSubscribers()
    }

    protected open fun initialize() {
    }

    protected open fun setupListeners() {
    }

    protected open fun setupSubscribers() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}