package meh.daniel.com.githubliteapp.presentation.screens.repositoryinfo

import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.noties.markwon.Markwon
import kotlinx.coroutines.flow.onEach
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentDetailrepositoryBinding
import meh.daniel.com.githubliteapp.presentation.base.BaseFragment
import meh.daniel.com.githubliteapp.presentation.utils.observeInLifecycle
import org.commonmark.node.Node

private const val ID_REPO = "id_repo"

@AndroidEntryPoint
class DetailRepositoryFragment : BaseFragment<DetailRepositoryViewModel, FragmentDetailrepositoryBinding>(R.layout.fragment_detailrepository){

    override val viewModel: DetailRepositoryViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailrepositoryBinding.inflate(inflater, container, false)

    override fun initialize() {
        arguments?.getString(ID_REPO)?.let {
            viewModel.loadDetailRepository(it.toInt())
        }
        initView()
    }

    override fun setupListeners() {
        binding.logoutBtn.setOnClickListener {
            findNavController().navigate(R.id.action_detailInfoFragment_to_authFragment)
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailInfoFragment_to_repositoriesListFragment)
        }
    }

    override fun setupSubscribers() {
        viewModel.state.onEach { state ->
            with(binding){
                content.visibility = if(state is DetailRepositoryState.Loaded) View.VISIBLE else View.GONE

                loadingView.root.visibility = if(state is DetailRepositoryState.Loading) View.VISIBLE else View.GONE
                errorView.root.visibility = if (state is DetailRepositoryState.Error && !state.isNoConnectionError) View.VISIBLE else View.GONE
                undefinedView.root.visibility = if (state is DetailRepositoryState.Error && state.isNoConnectionError) View.VISIBLE else View.GONE

                urlRepositoryTxt.text = if(state is DetailRepositoryState.Loaded) state.githubRepo.url else ""
                licence.text = if(state is DetailRepositoryState.Loaded) state.githubRepo.licence else ""
                countOfStars.text = if(state is DetailRepositoryState.Loaded) state.githubRepo.stars.toString() else "0"
                countOfForks.text = if(state is DetailRepositoryState.Loaded) state.githubRepo.forks.toString() else "0"
                countOfWatchers.text = if(state is DetailRepositoryState.Loaded) state.githubRepo.watchers.toString() else "0"

                scrollContentReadMe.visibility = if(state is DetailRepositoryState.Loaded) View.VISIBLE else View.GONE
                if(state is DetailRepositoryState.Loaded) {
                    val markwon = Markwon.create(requireContext().applicationContext)
                    val node: Node = markwon.parse(state.readme)
                    val markdown: Spanned = markwon.render(node)
                    markwon.setParsedMarkdown(readMeTxt, markdown)
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }
    private fun initView() {
        with(binding) {
            errorView.btnRefresh.btnText.text = getString(R.string.refresh)
            undefinedView.btnRefresh.btnText.text = getString(R.string.retry)
        }
    }
}