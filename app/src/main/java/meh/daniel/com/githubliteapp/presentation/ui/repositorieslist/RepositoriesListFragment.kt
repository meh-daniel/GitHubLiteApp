package meh.daniel.com.githubliteapp.presentation.ui.repositorieslist

import android.os.Bundle
import android.view.LayoutInflater
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

    override fun setupSubscribers() {
        observableViewModel()
    }

    private fun observableViewModel() {
        viewModel.state.observe(this) { state ->
            with(binding){
                if(state is RepositoriesListState.Loaded) repositoryAdapter.submitList(state.repos)
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