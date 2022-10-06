package meh.daniel.com.githubliteapp.presentation.ui.repositoryinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentDetailrepositoryBinding
import meh.daniel.com.githubliteapp.presentation.base.BaseFragment

class DetailRepositoryFragment : BaseFragment<DetailRepositoryViewModel, FragmentDetailrepositoryBinding>(R.layout.fragment_detailrepository){

    override val viewModel: DetailRepositoryViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailrepositoryBinding.inflate(inflater, container, false)
}