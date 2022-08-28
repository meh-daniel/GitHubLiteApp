package meh.daniel.com.githubliteapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.FragmentAuthBinding

class AuthFragment : Fragment(R.layout.fragment_auth){

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchUserBtn.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_repositoriesListFragment)
        }
    }

}