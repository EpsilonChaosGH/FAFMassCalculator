package com.example.fafmasscalculator.presentation.screens

import android.os.Bundle
import android.os.Parcel
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fafmasscalculator.ExpAdapter
import com.example.fafmasscalculator.ExpParcelable
import com.example.fafmasscalculator.R
import com.example.fafmasscalculator.databinding.FragmentExpBinding
import com.example.fafmasscalculator.domain.models.Exp

class ExpFragment : Fragment(R.layout.fragment_exp), ExpAdapter.Listener {
    private lateinit var binding: FragmentExpBinding
    private lateinit var adapter : ExpAdapter
    private val imageIdList = ArrayList<Exp>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExpBinding.bind(view)

        imageIdList.add(0, Exp(R.drawable.paragon,"Paragon",getString(R.string.paragon),ExpAdapter.AEON))
        imageIdList.add(1, Exp(R.drawable.salvation,"Salvation",getString(R.string.salvation),ExpAdapter.AEON))
        imageIdList.add(2, Exp(R.drawable.emissary,"Emissary",getString(R.string.emissary),ExpAdapter.AEON))
        imageIdList.add(3, Exp(R.drawable.czar,"Czar",getString(R.string.czar),ExpAdapter.AEON))
        imageIdList.add(4, Exp(R.drawable.galacticcolossus,"Colossus",getString(R.string.galacticcolossus),ExpAdapter.AEON))
        imageIdList.add(5, Exp(R.drawable.tempest,"Tempest",getString(R.string.trmpest),ExpAdapter.AEON))
        imageIdList.add(6, Exp(R.drawable.mavor,"Mavor",getString(R.string.mavor),ExpAdapter.UEF))
        imageIdList.add(7, Exp(R.drawable.duke,"Duke",getString(R.string.duke),ExpAdapter.UEF))
        imageIdList.add(8, Exp(R.drawable.novaxcenter,"Novax",getString(R.string.novaxcenter),ExpAdapter.UEF))
        imageIdList.add(9, Exp(R.drawable.fatboy,"Fatboy",getString(R.string.fatboy),ExpAdapter.UEF))
        imageIdList.add(10, Exp(R.drawable.atlantis,"Atlantis",getString(R.string.atlantis),ExpAdapter.UEF))
        imageIdList.add(11, Exp(R.drawable.scathis,"Scathis",getString(R.string.scathis),ExpAdapter.CYBRAN))
        imageIdList.add(12, Exp(R.drawable.disruptor,"Disruptor",getString(R.string.disruptor),ExpAdapter.CYBRAN))
        imageIdList.add(13, Exp(R.drawable.megalith,"Megalith",getString(R.string.megalith),ExpAdapter.CYBRAN))
        imageIdList.add(14, Exp(R.drawable.soulripper,"Soul Ripper",getString(R.string.soulripper),ExpAdapter.CYBRAN))
        imageIdList.add(15, Exp(R.drawable.monkeylord,"Monkeylord",getString(R.string.monkeylord),ExpAdapter.CYBRAN))
        imageIdList.add(16, Exp(R.drawable.yolonaoss,"Yolonaoss",getString(R.string.yolonaoss),ExpAdapter.SERAPHIM))
        imageIdList.add(17, Exp(R.drawable.hovatham,"Hovatham",getString(R.string.hovatham),ExpAdapter.SERAPHIM))
        imageIdList.add(18, Exp(R.drawable.ahwassa,"Ahwassa",getString(R.string.ahwassa),ExpAdapter.SERAPHIM))
        imageIdList.add(19, Exp(R.drawable.ythotha,"Ythotha",getString(R.string.ythotha),ExpAdapter.SERAPHIM))

        adapter = ExpAdapter(this)

        init()
        }

    private fun init() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(activity, 4)
            rcView.adapter = adapter
            adapter.addAll(imageIdList)
        }
      }

    override fun onClick(exp: Exp) {
      //  Toast.makeText(activity, exp.mass, Toast.LENGTH_SHORT).show()
        findNavController().previousBackStackEntry?.savedStateHandle
            ?.set(EXP,ExpParcelable(exp.imageId,exp.title,exp.mass,exp.type))
        findNavController().popBackStack()
    }
    companion object{
        const val EXP = "EXP"
    }
}


