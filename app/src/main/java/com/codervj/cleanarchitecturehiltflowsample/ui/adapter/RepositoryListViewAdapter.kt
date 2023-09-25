package com.codervj.cleanarchitecturehiltflowsample.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.codervj.cleanarchitecturehiltflowsample.R
import com.codervj.cleanarchitecturehiltflowsample.databinding.RowRepoListViewBinding

import com.codervj.cleanarchitecturehiltflowsample.domain.models.RepoDataRecord


class RepositoryListViewAdapter(private val repoList: ArrayList<RepoDataRecord>) :
    RecyclerView.Adapter<RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding =
            RowRepoListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = repoList[position]
        holder.binding.tvRepoName.text = "Repo Name : ${item.name}"
        holder.binding.tvRepoDescription.text = item.description ?: "NA"
        holder.binding.ivVisibility.setImageResource(if (item.visibility == "public") R.drawable.ic_unlocked else R.drawable.ic_locked)
        holder.binding.cardView.setOnClickListener {
            if (item.url.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                startActivity(holder.itemView.context, intent, null)
            }
        }
    }


}

class RepositoryViewHolder(val binding: RowRepoListViewBinding) :
    RecyclerView.ViewHolder(binding.root)