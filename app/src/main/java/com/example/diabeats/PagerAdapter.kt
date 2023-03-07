package com.example.diabeats

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private val TABTITLES = arrayOf("+Diabeats", "ListDiabeats", "ClassifyDiabeats")
    }

    override fun getItem(position: Int): Fragment {
        // instantiate a fragment for the page.
        if (position == 0) {
            return CreateDiabeatsFragment.newInstance(mContext)    } 
        else if (position == 1) {
            return ListDiabeatsFragment.newInstance(mContext)    } 
        else if (position == 2) {
            return ClassifyDiabeatsFragment.newInstance(mContext)    } 
        return CreateDiabeatsFragment.newInstance(mContext) 
    }

    override fun getPageTitle(position: Int): CharSequence {
        return TABTITLES[position]
    }

    override fun getCount(): Int {
        return 3
    }
}
