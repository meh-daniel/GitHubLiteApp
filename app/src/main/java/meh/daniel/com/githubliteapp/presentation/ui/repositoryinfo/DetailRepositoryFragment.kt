package meh.daniel.com.githubliteapp.presentation.ui.repositoryinfo

import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
    }

    override fun setupSubscribers() {
        viewModel.state.onEach { state ->
            with(binding){
                content.visibility = if(state is DetailRepositoryState.Loaded) View.VISIBLE else View.GONE
                loadingView.root.visibility = if(state is DetailRepositoryState.Loading) View.VISIBLE else View.GONE

                errorView.root.visibility = if (state is DetailRepositoryState.Error && state.isNoConnectionError) View.VISIBLE else View.GONE
                errorView.btnRefresh.btnText.text = "REFRESH"
                undefinedView.root.visibility = if (state is DetailRepositoryState.Error && state.isNoConnectionError) View.VISIBLE else View.GONE
                undefinedView.btnRefresh.btnText.text = "RETRY"

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
}