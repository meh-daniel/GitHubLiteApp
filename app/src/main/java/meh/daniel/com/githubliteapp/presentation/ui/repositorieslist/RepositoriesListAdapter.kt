package meh.daniel.com.githubliteapp.presentation.ui.repositorieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.githubliteapp.R
import meh.daniel.com.githubliteapp.databinding.ItemRepositoryBinding
import meh.daniel.com.domain.model.repository.Repository

class RepositoryAdapter : ListAdapter<Repository, RecyclerView.ViewHolder>(RepositoryDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when(viewType){
        R.layout.item_repository -> RepositoryViewHolder.from(parent)
        else ->  throw Throwable("onCreateViewHolder exception - unknown view type by name: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when(holder) {
        is RepositoryViewHolder -> holder.bind(item = getItem(position))
        else -> throw Throwable("onBindViewHolder exception - unknown holder of view by name ${holder.itemView.id}")
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is Repository -> R.layout.item_repository
        else -> throw Exception("getItemViewType unknown item class exception from position: $position")
    }
}

class RepositoryViewHolder(private val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Repository){
        binding.nameRepositoryTxt.text = item.name
        binding.programmingLanguageTxt.text = item.language
        if(item.description != null){
            binding.repositoryDescription.visibility = View.VISIBLE
            binding.repositoryDescription.text = item.description
        }
    }

    companion object {
        fun from(parent: ViewGroup): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRepositoryBinding.inflate(layoutInflater, parent, false)
            return RepositoryViewHolder(binding)
        }
    }
}

class RepositoryDiffUtil: DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(
        oldItem: Repository,
        newItem: Repository
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: Repository,
        newItem: Repository
    ): Boolean = oldItem == newItem
}