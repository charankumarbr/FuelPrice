package `in`.charan.fuelprice.model

/**
Author: Charan Kumar
Date: 2019-07-16
 */
data class Brand(val name: String) {

    companion object {

        const val BRAND_BP = "BP"
        const val BRAND_HP = "HP"
        const val BRAND_IOC = "IOC"

        @JvmStatic
        fun getAllBrands(): ArrayList<Brand> {
            val brands = ArrayList<Brand>()
            brands.add(Brand(BRAND_BP))
            brands.add(Brand(BRAND_HP))
            brands.add(Brand(BRAND_IOC))
            return brands
        }
    }
}