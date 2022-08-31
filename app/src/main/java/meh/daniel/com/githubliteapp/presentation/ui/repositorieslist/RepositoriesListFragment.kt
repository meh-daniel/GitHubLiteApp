package meh.daniel.com.githubliteapp.presentation.ui.repositorieslist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentRepositorieslistBinding
import meh.daniel.com.githubliteapp.presentation.base.BaseFragment

@AndroidEntryPoint
class RepositoriesListFragment : BaseFragment<RepositoriesListViewModel, FragmentRepositorieslistBinding>(R.layout.fragment_repositorieslist){

    override val viewModel: RepositoriesListViewModel by viewModels()

    private val repositoryAdapter = RepositoryAdapter()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRepositorieslistBinding.inflate(inflater, container, false)

    override fun initialize() {
        super.initialize()
        initRecyclerView()
        observableViewModel()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun observableViewModel() {
        viewModel.repositories.observe(this@RepositoriesListFragment) {
            repositoryAdapter.submitList(it)
        }
    }

    private fun initRecyclerView() {
        binding.repositoriesRv.adapter = repositoryAdapter
        binding.repositoriesRv.layoutManager =
            LinearLayoutManager(
                this.context,
                LinearLayoutManager.VERTICAL,
                false
            )
    }

}