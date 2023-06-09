package com.adrian.githubapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.githubapp.databinding.ItemUserBinding
import com.adrian.githubapp.model.GithubUser
import com.adrian.githubapp.ui.activity.DetailActivity
import com.bumptech.glide.Glide

class FollowingAdapter(private val listFollowing: List<GithubUser>) : RecyclerView.Adapter<FollowingAdapter.ViewHolder>(){
    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemUserBinding.inflate(
        LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val following = listFollowing[position]

        with(holder.binding) {
            Glide.with(root.context).load(following.avatarUrl).circleCrop().into(profile)
            username.text = following.login
            url.text = following.htmlUrl

            root.setOnClickListener {
                val intent = Intent(root.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, following)
                root.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = listFollowing.size
}