package `in`.charan.fuelprice.ui.choosestate

import `in`.charan.fuelprice.R
import `in`.charan.fuelprice.model.State
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView

/**
Author: Charan Kumar
Date: 2019-07-16
 */
class ChooseStateAdapter(private val context: Context, private val states: ArrayList<State>) :
    RecyclerView.Adapter<StateViewHolder>() {

    private var selectedPosition = -1

    private var stateSelectedListener: OnStateSelectedListener

    @ColorRes
    private var textColor: Int = R.color.text_primary

    init {
        if (context is OnStateSelectedListener) {
            stateSelectedListener = context
        } else {
            throw RuntimeException("$context needs to implement OnStateSelectedListener")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_choose_state,
            parent, false
        )
        return StateViewHolder(view)
    }

    override fun getItemCount() = states.size

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
        holder.tvName.text = states[position].name
        holder.view.tag = position
        holder.view.setOnClickListener(clickListener)
        if (selectedPosition == position) {
            holder.tvName.isSelected = true
            textColor = android.R.color.white

        } else {
            holder.tvName.isSelected = false
            textColor = R.color.text_primary
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.tvName.setTextColor(context.resources.getColor(textColor, null))

        } else {
            holder.tvName.setTextColor(context.resources.getColor(textColor))
        }
        holder.vDivider.visibility = if (position == (itemCount - 1)) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
    }

    private val clickListener = View.OnClickListener {
        when (it.id) {
            R.id.lcsLayout -> {
                val clickedPos = it.tag as Int
                selectedPosition = if (clickedPos == selectedPosition) {
                    stateSelectedListener.onStateSelected(null)
                    -1
                } else {
                    stateSelectedListener.onStateSelected(states[clickedPos])
                    clickedPos
                }
                notifyDataSetChanged()
            }
        }
    }

}

class StateViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvName: TextView = view.findViewById(R.id.lcsTvName)
    val vDivider: View = view.findViewById(R.id.lcsVDivider)
}

interface OnStateSelectedListener {
    fun onStateSelected(selectedState: State?)
}