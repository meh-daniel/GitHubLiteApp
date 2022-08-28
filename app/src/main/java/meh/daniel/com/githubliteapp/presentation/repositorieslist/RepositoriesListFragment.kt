package meh.daniel.com.githubliteapp.presentation.repositorieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentRepositorieslistBinding

class RepositoriesListFragment : Fragment(R.layout.fragment_repositorieslist){

    private lateinit var binding: FragmentRepositorieslistBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepositorieslistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}