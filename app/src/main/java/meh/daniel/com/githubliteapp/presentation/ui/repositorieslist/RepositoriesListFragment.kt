package meh.daniel.com.githubliteapp.presentation.ui.repositorieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentRepositorieslistBinding
import meh.daniel.com.githubliteapp.presentation.base.BaseFragment

private const val ID_REPO = "id_repo"

@AndroidEntryPoint
class RepositoriesListFragment : BaseFragment<RepositoriesListViewModel, FragmentRepositorieslistBinding>(R.layout.fragment_repositorieslist){

    override val viewModel: RepositoriesListViewModel by viewModels()
    private val repositoryAdapter = RepositoryAdapter(
        onClickRepo = { routeToDetails(it) }
    )

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRepositorieslistBinding.inflate(inflater, container, false)

    override fun initialize() {
        initRecyclerView()
    }

    override fun setupListeners() {
        binding.logoutBtn.setOnClickListener {
            viewModel.logout()
        }
        binding.emptyView.btnRefresh.root.setOnClickListener {
            viewModel.loadRepositories()
        }
        binding.errorView.btnRefresh.root.setOnClickListener {
            viewModel.loadRepositories()
        }
        binding.undefinedView.btnRefresh.root.setOnClickListener {
            viewModel.loadRepositories()
        }
        binding.logoutBtn.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.action_repositoriesListFragment_to_authFragment)
        }
    }

    override fun setupSubscribers() {
        observableViewModel()
    }

    private fun observableViewModel() {
        viewModel.state.observe(this) { state ->
            with(binding){
                errorView.btnRefresh.btnText.text = "REFRESH"
                undefinedView.btnRefresh.btnText.text = "RETRY"
                emptyView.btnRefresh.btnText.text = "RETRY"
                if(state is RepositoriesListState.Loaded) repositoryAdapter.submitList(state.repos)
                loadingView.root.visibility = if(state is RepositoriesListState.Loading) View.VISIBLE else View.GONE
                emptyView.root.visibility = if (state is RepositoriesListState.Empty) View.VISIBLE else View.GONE
                errorView.root.visibility = if (state is RepositoriesListState.Error && state.isNoConnectionError) View.VISIBLE else View.GONE
                undefinedView.root.visibility = if (state is RepositoriesListState.Error && !state.isNoConnectionError) View.VISIBLE else View.GONE
            }
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

    private fun routeToDetails(id: Int){
        val bundle = Bundle()
        bundle.putString(ID_REPO, id.toString())
        findNavController().navigate(R.id.action_repositoriesListFragment_to_detailInfoFragment, bundle)
    }

}