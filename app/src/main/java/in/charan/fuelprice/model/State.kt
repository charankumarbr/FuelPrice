package `in`.charan.fuelprice.model

import android.os.Parcel
import android.os.Parcelable
import javax.inject.Inject

/**
Author: Charan Kumar
Date: 2019-07-16
 */
data class State @Inject constructor(val name: String, val hpCode: String, val iocCode: String) :
    Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(name)
        p0?.writeString(hpCode)
        p0?.writeString(iocCode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<State> {
        override fun createFromParcel(parcel: Parcel): State {
            return State(parcel)
        }

        override fun newArray(size: Int): Array<State?> {
            return arrayOfNulls(size)
        }

        private val names = arrayListOf(
            "Andaman and Nicobar Islands", "Andhra Pradesh", "Arunachal Pradesh",
            "Assam", "Bihar", "Chandigarh", "Chhattisgarh", "Dadra and Nagar Haveli",
            "Daman and Diu", "Delhi", "Goa", "Gujarat", "Haryana", "Himachal Pradesh",
            "Jammu & Kashmir", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh",
            "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland",
            "Orissa", "Pondicherry", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana",
            "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"
        )

        private val hpCodes = arrayListOf(
            "", "AP1", "", "AS", "BR", "CH", "CT", "DN", "DD", "DL", "GA", "GJ", "HR", "HP", "JK", "JH",
            "KA", "KL", "", "MP", "MH", "MN", "ML", "MZ", "NL", "OR", "PY", "PB", "RJ", "SK", "TN",
            "TG", "TR", "UP", "UT", "WB"
        )

        private val iocCodes = arrayListOf(
            "AN", "AP", "ARP", "AS", "BH", "CD", "CSG", "DH", "DD", "DEL", "GDD", "GJ", "HR", "HP",
            "JK", "JRK", "KAR", "KER", "", "MP", "MAH", "MNP", "MGL", "MZ", "NG", "OR", "PY", "PB",
            "RJ", "SK", "TN", "TG", "TRP", "UP", "UTK", "WB"
        )

        @JvmStatic
        fun getAllStates(): ArrayList<State> {
            val states = arrayListOf<State>()
            val length = names.size
            for (index in 0 until length) {
                states.add(State(names[index], hpCodes[index], iocCodes[index]))
            }
            return states
        }
    }
}