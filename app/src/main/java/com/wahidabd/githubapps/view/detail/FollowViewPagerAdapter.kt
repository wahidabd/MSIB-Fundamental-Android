package com.wahidabd.githubapps.view.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wahidabd.githubapps.view.detail.follow.FollowerFragment
import com.wahidabd.githubapps.view.detail.follow.FollowingFragment

class FollowViewPagerAdapter(
    fragment: Fragment,
    private val username: String
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when(position){
            0 -> FollowerFragment.newInstance(username)
            else -> FollowingFragment.newInstance(username)
        }
}