package `in`.charan.fuelprice.ui.home

import `in`.charan.fuelprice.model.Brand
import `in`.charan.fuelprice.model.State
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
Author: Charan Kumar
Date: 2019-07-16
 */
class BrandFragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    lateinit var selectedState: State

    lateinit var brands: ArrayList<Brand>

    override fun getItem(position: Int): BrandFragment {
        val currentBrand = brands[position].name
        if (currentBrand == Brand.BRAND_HP) {
            return BrandFragment.newInstance(brands[position].name, selectedState.hpCode)

        } else if (currentBrand == Brand.BRAND_IOC) {
            return BrandFragment.newInstance(brands[position].name, selectedState.iocCode)
        }
        return BrandFragment()
    }

    override fun getCount() = brands.size

    override fun getPageTitle(position: Int): CharSequence {
        return brands[position].name
    }
}