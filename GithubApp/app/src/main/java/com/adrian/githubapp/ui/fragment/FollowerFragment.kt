package com.adrian.githubapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.githubapp.R
import com.adrian.githubapp.adapter.FollowerAdapter
import com.adrian.githubapp.databinding.FragmentFollowerBinding
import com.adrian.githubapp.model.GithubUser
import com.adrian.githubapp.model.viewmodel.FollowerViewModel
import com.adrian.githubapp.ui.activity.DetailActivity


class FollowerFragment : Fragment() {
    private lateinit var binding: FragmentFollowerBinding
    private lateinit var followerViewModel: FollowerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followerViewModel = ViewModelProvider(this).get(FollowerViewModel::class.java)
        followerViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressFollower.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        followerViewModel.follower.observe(viewLifecycleOwner) { listFollower ->
            setDataToFragment(listFollower)
        }
        followerViewModel.getFollower(arguments?.getString(DetailActivity.EXTRA_FRAGMENT).toString())
    }

    private fun setDataToFragment(listFollower: List<GithubUser>) {
        binding.rvFollower.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFollower.adapter = FollowerAdapter(listFollower)
    }
}