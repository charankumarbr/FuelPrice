package `in`.charan.fuelprice.ui.home

import `in`.charan.fuelprice.R
import `in`.charan.fuelprice.di.choosestate.RupeeSymbol
import `in`.charan.fuelprice.model.ResponseHP
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

/**
Author: Charan Kumar
Date: 2019-07-17
 */
class HPAdapter @Inject constructor(@RupeeSymbol private val rupeeSymbol: String) :
    RecyclerView.Adapter<HPViewHolder>() {

    lateinit var hpData: ArrayList<ResponseHP>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HPViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_hp, parent, false)
        return HPViewHolder(view)
    }

    override fun getItemCount() = if (this::hpData.isInitialized) hpData.size else 0

    override fun onBindViewHolder(holder: HPViewHolder, position: Int) {
        val responseHP = hpData[position]
        holder.tvPlace.text = responseHP.townname
        holder.tvPetrol.text = "$rupeeSymbol ${responseHP.petrol}"
        holder.tvDiesel.text = "$rupeeSymbol ${responseHP.diesel}"
    }
}

class HPViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tvPlace: TextView = view.findViewById(R.id.lhTvPlace)
    val tvPetrol: TextView = view.findViewById(R.id.lhTvPetrol)
    val tvDiesel: TextView = view.findViewById(R.id.lhTvDiesel)
}