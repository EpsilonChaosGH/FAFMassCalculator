package com.example.fafmasscalculator

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fafmasscalculator.databinding.ResultItemBinding
import com.example.fafmasscalculator.domain.models.Result

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ResultHolder>() {

    class ResultHolder(
        val binding: ResultItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    var resultList = emptyList<Result>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            resultList
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResultItemBinding.inflate(inflater, parent, false)

        return ResultHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        val result = resultList[position]

        holder.binding.resultViewSacu.text = result.sacu.toString()
        holder.binding.resultViewMassIncome.text = result.massIncome.toString()
        holder.binding.resultViewTime.text = result.time.toString()

        if (result.best) {
            holder.binding.resultItem.setBackgroundResource(R.color.green_aeon)
            holder.binding.timeImageView.setImageResource(R.drawable.ic_star)
        } else {
            holder.binding.resultItem.setBackgroundResource(R.color.trans)
            holder.binding.timeImageView.setImageResource(R.drawable.ic__time)
        }
    }

    override fun getItemCount() = resultList.size
}