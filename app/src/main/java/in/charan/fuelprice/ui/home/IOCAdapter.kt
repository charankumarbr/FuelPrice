package `in`.charan.fuelprice.ui.home

import `in`.charan.fuelprice.R
import `in`.charan.fuelprice.di.choosestate.RupeeSymbol
import `in`.charan.fuelprice.model.CustomResponseIOC
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

/**
Author: Charan Kumar
Date: 2019-07-18
 */
class IOCAdapter @Inject constructor(@RupeeSymbol private val rupeeSymbol: String) :
    RecyclerView.Adapter<IOCViewHolder>() {

    lateinit var iocData: ArrayList<CustomResponseIOC>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IOCViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_hp, parent, false)
        return IOCViewHolder(view)
    }

    override fun getItemCount() = if (this::iocData.isInitialized) iocData.size else 0

    override fun onBindViewHolder(holder: IOCViewHolder, position: Int) {
        val customResponseIOC = iocData[position]
        holder.tvPlace.text = customResponseIOC.district
        holder.tvPetrol.text = "Min: $rupeeSymbol ${customResponseIOC.modifiedResponseIOC.minPetrol}" +
                " - Max: $rupeeSymbol ${customResponseIOC.modifiedResponseIOC.maxPetrol}"
        holder.tvDiesel.text = "Min: $rupeeSymbol ${customResponseIOC.modifiedResponseIOC.minDiesel}" +
                " - Max: $rupeeSymbol ${customResponseIOC.modifiedResponseIOC.maxDiesel}"
    }

}

/*class IOCDistrictViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    val tvDistrict: TextView = view.findViewById(R.id.ldTvPlace)
}

class IOCPumpViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    val tvPump: TextView = view.findViewById(R.id.lpTvPump)
    val tvPetrol: TextView = view.findViewById(R.id.lpTvPetrol)
    val tvDiesel: TextView = view.findViewById(R.id.lpTvDiesel)
}*/

class IOCViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val tvPlace: TextView = view.findViewById(R.id.lhTvPlace)
    val tvPetrol: TextView = view.findViewById(R.id.lhTvPetrol)
    val tvDiesel: TextView = view.findViewById(R.id.lhTvDiesel)
}