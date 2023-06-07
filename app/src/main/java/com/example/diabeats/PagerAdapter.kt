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
            return when (position) {
                0 -> { 
                    CreateDiabeatsFragment.newInstance(mContext) 
                }            1 -> { 
                    ListDiabeatsFragment.newInstance(mContext) 
                }            2 -> { 
                    ClassifyDiabeatsFragment.newInstance(mContext) 
                }
                else -> CreateDiabeatsFragment.newInstance(mContext) 
             }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return TABTITLES[position]
    }

    override fun getCount(): Int {
        return 3
    }
}
