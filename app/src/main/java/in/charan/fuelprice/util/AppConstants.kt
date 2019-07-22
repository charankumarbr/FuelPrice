package `in`.charan.fuelprice.util

/**
Author: Charan Kumar
Date: 2019-07-15
 */
sealed class AppConstants {

    companion object {
        const val SPLASH_DELAY = 750L
    }

    class BundleConstants {
        companion object {
            const val BRAND = "BRAND"
            const val STATE_CODE = "STATE_CODE"
        }
    }

    class PrefConstants {
        companion object {
            const val STATE_NAME = "state_name"
            const val STATE_HP_CODE = "state_hp_code"
            const val STATE_IOC_CODE = "state_ioc_code"
            const val STATE_LAT1 = "state_lat1"
            const val STATE_LONG1 = "state_lon1"
            const val STATE_LAT2 = "state_lat2"
            const val STATE_LONG2 = "state_lon2"
        }
    }

    class UrlConstants {
        companion object {
            const val X_RapidAPI_Key = "YOUR_KEY_HERE"
            const val X_Rapid_Host = "daily-fuel-prices-india.p.rapidapi.com"
        }
    }

}