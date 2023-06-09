package com.adrian.githubapp.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.adrian.githubapp.ui.fragment.FollowerFragment
import com.adrian.githubapp.ui.fragment.FollowingFragment

class ViewPagerAdapter(activity: AppCompatActivity, private val login: Bundle) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> FollowerFragment()
        1 -> FollowingFragment()
        else -> throw IllegalStateException("Invalid fragment position")
    }.apply {
        arguments = login
    }
}