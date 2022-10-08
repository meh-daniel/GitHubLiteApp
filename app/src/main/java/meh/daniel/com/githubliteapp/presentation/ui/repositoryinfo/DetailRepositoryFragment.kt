package meh.daniel.com.githubliteapp.presentation.ui.repositoryinfo

import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.noties.markwon.Markwon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentDetailrepositoryBinding
import meh.daniel.com.githubliteapp.presentation.base.BaseFragment
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
        viewModel.state.observe(this){ state ->
            with(binding){
                urlRepositoryTxt.text = if(state is DetailRepositoryState.Loaded) state.githubRepo.url else ""
                licence.text = if(state is DetailRepositoryState.Loaded) state.githubRepo.licence else "nothing"
                countOfStars.text = if(state is DetailRepositoryState.Loaded) state.githubRepo.stars.toString() else "0"
                countOfForks.text = if(state is DetailRepositoryState.Loaded) state.githubRepo.forks.toString() else "0"
                countOfWatchers.text = if(state is DetailRepositoryState.Loaded) state.githubRepo.watchers.toString() else "0"
                if(state is DetailRepositoryState.Loaded && state.readmeState is DetailRepositoryState.ReadmeState.Loaded){
                    val markwon = Markwon.create(context!!.applicationContext)
                    val node: Node = markwon.parse(state.readmeState.markdown)
                    val markdown: Spanned = markwon.render(node)
                    markwon.setParsedMarkdown(readMeTxt, markdown)
                }
            }
        }
    }
}