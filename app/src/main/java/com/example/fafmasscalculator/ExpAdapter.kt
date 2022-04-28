package com.example.fafmasscalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fafmasscalculator.databinding.ExpItemBinding
import com.example.fafmasscalculator.domain.models.Exp


class ExpAdapter(var listener: Listener): RecyclerView.Adapter<ExpAdapter.ExpHolder>() {

    var expList = ArrayList<Exp>()

   class ExpHolder (item: View): RecyclerView.ViewHolder(item){
       private val binding = ExpItemBinding.bind(item)
       fun bind(exp: Exp, listener: Listener) = with(binding){
           im.setImageResource(exp.imageId)
           tvTitle.text = exp.title
           tvMass.text = exp.mass
           clickView.setOnClickListener{
               listener.onClick(exp)
           }
       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpHolder {
        val view = when(viewType) {
            AEON  -> LayoutInflater.from(parent.context).inflate(R.layout.aeon_item, parent, false)
            CYBRAN  -> LayoutInflater.from(parent.context).inflate(R.layout.cybran_item, parent, false)
            SERAPHIM  -> LayoutInflater.from(parent.context).inflate(R.layout.seraphim_item, parent, false)
            UEF  -> LayoutInflater.from(parent.context).inflate(R.layout.uef_item, parent, false)
            else -> throw IllegalAccessException("invalid type")
        }
        return ExpHolder(view)
    }

    override fun onBindViewHolder(holder: ExpHolder, position: Int) {
        holder.bind(expList[position], listener)
    }

    override fun getItemCount(): Int {
        return expList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(expList[position].type){
            0 -> AEON
            1 -> CYBRAN
            2 -> SERAPHIM
            3 -> UEF
            else -> throw IllegalAccessException("invalid type")
        }
    }

    fun addAll(list: List<Exp>){
        expList.addAll(list)
        //notifyDataSetChanged()
    }
    interface Listener {
        fun onClick(exp: Exp)
    }
    companion object{
        const val AEON = 0
        const val CYBRAN = 1
        const val SERAPHIM = 2
        const val UEF = 3
    }
}
