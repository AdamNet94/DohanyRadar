package hu.bme.aut.android.dohanyradarapp.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class TobaccoPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragmentList = mutableListOf<Fragment>()
    private val mFragmentTitleList = mutableListOf<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        mFragmentList.removeAt(position)
    }

    override fun getCount() : Int = mFragmentList.size

    fun addFrag(
        fragment: Fragment?,
        title: String?
    ) {
        mFragmentList.add(fragment!!)
        mFragmentTitleList.add(title!!)
    }

    override fun getPageTitle(position: Int): String {
        return mFragmentTitleList[position]
    }
}