package meh.daniel.com.githubliteapp.presentation.repositorieslist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentRepositorieslistBinding

@AndroidEntryPoint
class RepositoriesListFragment : Fragment(R.layout.fragment_repositorieslist){

    private lateinit var binding: FragmentRepositorieslistBinding
    private val viewModel: RepositoriesListViewModel by viewModels()

    private val repositoryAdapter = RepositoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositorieslistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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