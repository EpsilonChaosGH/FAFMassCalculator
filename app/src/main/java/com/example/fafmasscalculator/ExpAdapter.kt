package com.example.fafmasscalculator

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fafmasscalculator.databinding.ExpItemBinding
import com.example.fafmasscalculator.domain.models.Exp

interface Listener {
    fun onClick(exp: Exp)
}

class ExpAdapter(
    var listener: Listener
) : RecyclerView.Adapter<ExpAdapter.ExpHolder>(), View.OnClickListener {

    class ExpHolder(
        val binding: ExpItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    var expList = ArrayList<Exp>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onClick(v: View) {
        val exp = v.tag as Exp

        when (v.id) {
            R.id.expItem -> listener.onClick(exp)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExpItemBinding.inflate(inflater, parent, false)

        binding.expItem.setOnClickListener(this)

        return ExpHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpHolder, position: Int) {
        val exp = expList[position]

        holder.binding.expItem.tag = exp

        holder.binding.im.setImageResource(exp.imageId)
        holder.binding.tvTitle.text = exp.title
        holder.binding.tvMass.text = exp.mass

        when (exp.type) {
            AEON -> holder.binding.tvTitle.setBackgroundResource(R.color.green_aeon)
            UEF -> holder.binding.tvTitle.setBackgroundResource(R.color.blue_uef)
            CYBRAN -> holder.binding.tvTitle.setBackgroundResource(R.color.red_cybran)
            SERAPHIM -> holder.binding.tvTitle.setBackgroundResource(R.color.yellow_seraphim)
        }
    }

    override fun getItemCount() = expList.size

    companion object {
        const val AEON = "AEON"
        const val UEF = "UEF"
        const val CYBRAN = "CYBRAN"
        const val SERAPHIM = "SERAPHIM"
    }

}
