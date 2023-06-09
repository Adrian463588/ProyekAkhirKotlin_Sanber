package com.adrian.githubapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.githubapp.databinding.ItemUserBinding
import com.adrian.githubapp.model.GithubUser
import com.adrian.githubapp.ui.activity.DetailActivity
import com.bumptech.glide.Glide

class FollowerAdapter(private val listFollower: List<GithubUser>) : RecyclerView.Adapter<FollowerAdapter.ViewHolder>(){
    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemUserBinding.inflate(
        LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val follower = listFollower[position]

        with(holder.binding) {
            Glide.with(root.context).load(follower.avatarUrl).circleCrop().into(profile)
            username.text = follower.login
            url.text = follower.htmlUrl

            root.setOnClickListener {
                val intent = Intent(root.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, follower)
                root.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = listFollower.size
}