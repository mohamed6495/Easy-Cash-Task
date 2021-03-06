package com.mina_mikhail.base_mvvm.presentation.base

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyViewPagerAdapter(
  fragment: Fragment,
  private val fragmentList: List<Fragment>
) : FragmentStateAdapter(fragment) {

  override
  fun getItemCount() = fragmentList.size

  override
  fun createFragment(position: Int): Fragment {
    return fragmentList[position]
  }
}