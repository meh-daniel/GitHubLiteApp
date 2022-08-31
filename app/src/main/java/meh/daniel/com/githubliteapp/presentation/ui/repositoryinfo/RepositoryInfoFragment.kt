package meh.daniel.com.githubliteapp.presentation.ui.repositoryinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentRepositorieslistBinding
import meh.daniel.com.githubliteapp.databinding.FragmentRepositoryinfoBinding
import meh.daniel.com.githubliteapp.presentation.base.BaseFragment
import meh.daniel.com.githubliteapp.presentation.base.BaseViewModel

class RepositoryInfoFragment : BaseFragment<RepositoryInfoViewModel, FragmentRepositoryinfoBinding>(R.layout.fragment_repositoryinfo){

    override val viewModel: RepositoryInfoViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRepositoryinfoBinding.inflate(inflater, container, false)
}