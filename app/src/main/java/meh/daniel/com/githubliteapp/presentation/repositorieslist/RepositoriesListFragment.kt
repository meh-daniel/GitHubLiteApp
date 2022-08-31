package meh.daniel.com.githubliteapp.presentation.repositorieslist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentRepositorieslistBinding
import meh.daniel.com.githubliteapp.presentation.BaseFragment

@AndroidEntryPoint
class RepositoriesListFragment : BaseFragment<FragmentRepositorieslistBinding>(R.layout.fragment_repositorieslist, R.id.repositoriesListFragment){

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRepositorieslistBinding.inflate(inflater, container, false)


    private val viewModel: RepositoriesListViewModel by viewModels()
    private val repositoryAdapter = RepositoryAdapter()

   // observableViewModel()

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