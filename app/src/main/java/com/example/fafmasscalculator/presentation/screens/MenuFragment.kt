package com.example.fafmasscalculator.presentation.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fafmasscalculator.ExpParcelable
import com.example.fafmasscalculator.R
import com.example.fafmasscalculator.ResultAdapter
import com.example.fafmasscalculator.databinding.FragmentMenuBinding
import com.example.fafmasscalculator.domain.models.MassAndMps
import com.example.fafmasscalculator.domain.models.Result

import com.example.fafmasscalculator.presentation.MenuVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var massAndMps : MassAndMps
    private  val vm  by viewModel<MenuVM>()
    private val adapter = ResultAdapter()
    private var resultList: MutableList<Result> = ArrayList()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        vm.load()

        vm.resultLive.observe(viewLifecycleOwner) {
            resultList = it.resultList
        }

        vm.massAndMpsLive.observe(viewLifecycleOwner) {
            binding.massNeedEdit.setText(it.mass.toString())
            binding.mpsEdit.setText(it.mps.toString())
        }

        fun saveMassAndMpsSharedPreferences() {
            massAndMps = MassAndMps(
            mass = binding.massNeedEdit.text.toString().toInt(),
            mps = binding.mpsEdit.text.toString().toInt()
            )
            vm.save(massAndMps)
        }


        binding.imageViewMenu.setOnClickListener{
            openExp()
        }

        binding.btResult.setOnClickListener {
            if (binding.mpsEdit.text.isNotEmpty() && binding.massNeedEdit.text.isNotEmpty()) {
                massAndMps = MassAndMps(
                mass = binding.massNeedEdit.text.toString().toInt(),
                mps = binding.mpsEdit.text.toString().toInt()
                )
                vm.getResultList(massAndMps)

                saveMassAndMpsSharedPreferences()
                init()
            }
        }

        val liveData = findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<ExpParcelable>(
            ExpFragment.EXP
        )
        liveData?.observe(viewLifecycleOwner) { exp ->
            if (exp != null) {
                binding.massNeedEdit.setText(exp.mass)
                binding.imageViewMenu.setImageResource(exp.imageId)
                liveData.value = null
            }
        }
    }

    private fun init(){
        binding.apply{
            rcViewResult.layoutManager = GridLayoutManager(activity,1)
            rcViewResult.adapter = adapter
            adapter.addAll(resultList)
            resultList.clear()
        }
    }

    private fun openExp(){
        val direction = MenuFragmentDirections.actionMenuFragmentToExpFragment()
        findNavController().navigate(direction)
    }
}