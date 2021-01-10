package hu.bme.aut.android.dohanyradarapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hu.bme.aut.android.dohanyradarapp.fragment.StoresListFragment

class TobaccoPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment = when(position){
        0 -> StoresListFragment()
        1 -> StoresListFragment()
        else -> StoresListFragment()
    }

    override fun getCount() : Int = NUM_PAGES

    companion object{
        const val NUM_PAGES = 2
    }
}