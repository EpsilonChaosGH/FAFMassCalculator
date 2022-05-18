package com.example.fafmasscalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fafmasscalculator.databinding.ResultItemBinding
import com.example.fafmasscalculator.domain.models.Result


class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ResultHolder>() {

    private var resultList = ArrayList<Result>()

    class ResultHolder(item : View): RecyclerView.ViewHolder(item){
        private val binding = ResultItemBinding.bind(item)

        fun bind (result : Result) = with(binding){
            resultViewSacu.text = result.sacu.toString()
            resultViewMps.text =  result.mpsNumber.toString()
            resultViewTime.text = result.sec.toString()
        }
        fun bindName () = with(binding){
            resultViewSacu.text = "SACU"
            resultViewMps.text =  "MPS"
            resultViewTime.text = "TIME"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
        val view = when (viewType){
            TYPE_ITEM0 -> LayoutInflater.from(parent.context).inflate(R.layout.best_result_item,parent,false)
            TYPE_ITEM1 -> LayoutInflater.from(parent.context).inflate(R.layout.result_item,parent,false)
            else -> throw IllegalAccessException("invalid type")
        }
        return ResultHolder(view)
    }

    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        if (position == 0) holder.bindName()
        else holder.bind(resultList[position])
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun getItemViewType(position: Int): Int {
           return if (resultList[position].best) TYPE_ITEM0
            else TYPE_ITEM1
    }

    fun addAll(list: List<Result>){
        resultList.clear()
        resultList.addAll(list)
       // notifyDataSetChanged()
    }
    companion object{
        @JvmStatic private val TYPE_ITEM0 = 0
        @JvmStatic private val TYPE_ITEM1 = 1
    }
}