package com.adrian.githubapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrian.githubapp.databinding.ItemUserBinding
import com.adrian.githubapp.model.GithubUser
import com.adrian.githubapp.ui.activity.DetailActivity
import com.bumptech.glide.Glide

class SearchAdapter(private val listUser: List<GithubUser>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(ItemUserBinding.inflate(
        LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listUser[position]

        with(holder.binding) {
            Glide.with(root.context).load(user.avatarUrl).circleCrop().into(profile)
            username.text = user.login
            url.text = user.htmlUrl

            root.setOnClickListener {
                val intent = Intent(root.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, user)
                root.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = listUser.size
}