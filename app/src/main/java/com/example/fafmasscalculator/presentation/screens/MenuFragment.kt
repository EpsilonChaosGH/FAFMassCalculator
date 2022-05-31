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
import com.example.fafmasscalculator.domain.models.Params
import com.example.fafmasscalculator.domain.models.Result

import com.example.fafmasscalculator.presentation.MenuVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var params: Params
    private val vm by viewModel<MenuVM>()
    private val adapter = ResultAdapter()
    private var resultList: MutableList<Result> = ArrayList()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        vm.load()

        vm.resultLive.observe(viewLifecycleOwner) {
            resultList = it.resultList
        }

        vm.paramsLive.observe(viewLifecycleOwner) {
            binding.editTextMassCost.setText(it.massCost.toString())
            binding.editTextMassIncome.setText(it.massIncome.toString())
        }

        fun currentParams(): Params {
            params = Params(
                massCost = binding.editTextMassCost.text.toString().toInt(),
                massIncome = binding.editTextMassIncome.text.toString().toInt()
            )
            return params
        }

        binding.imageViewMenu.setOnClickListener {
            openExp()
        }

        binding.btResult.setOnClickListener {
            resultList.clear()
            vm.getResultList(currentParams())
            vm.save(currentParams())
            init()
        }

        val liveData =
            findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<ExpParcelable>(
                ExpFragment.EXP
            )
        liveData?.observe(viewLifecycleOwner) { exp ->
            if (exp != null) {
                binding.editTextMassCost.setText(exp.mass)
                binding.imageViewMenu.setImageResource(exp.imageId)
                liveData.value = null
            }
        }
        init()
    }

    private fun init() {
        binding.apply {
            rcViewResult.layoutManager = GridLayoutManager(activity, 1)
            rcViewResult.adapter = adapter
            adapter.addAll(resultList)
        }
    }

    private fun openExp() {
        val direction = MenuFragmentDirections.actionMenuFragmentToExpFragment()
        findNavController().navigate(direction)
    }
}