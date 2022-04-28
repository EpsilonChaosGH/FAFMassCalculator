package com.example.fafmasscalculator.presentation.screens

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fafmasscalculator.R
import com.example.fafmasscalculator.ResultAdapter
import com.example.fafmasscalculator.data.repository.MassAndMpsRepositoryImpl
import com.example.fafmasscalculator.data.repository.ResultListRepositoryImpl
import com.example.fafmasscalculator.data.storage.SharedPrefMassAndMpsStorage
import com.example.fafmasscalculator.databinding.FragmentMenuBinding
import com.example.fafmasscalculator.domain.models.MassAndMps
import com.example.fafmasscalculator.domain.models.Result
import com.example.fafmasscalculator.domain.usercase.GetMassAndMpsUseCase
import com.example.fafmasscalculator.domain.usercase.GetResultListUseCase
import com.example.fafmasscalculator.domain.usercase.SaveMassAndMpsUseCase
import com.example.fafmasscalculator.presentation.MenuVM
import com.example.fafmasscalculator.presentation.MenuViewModelFactory

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var massAndMps : MassAndMps
    private lateinit var getMassAndMps : MassAndMps
    private lateinit var vm :ViewModel
    private val adapter = ResultAdapter()
    private var resultList: MutableList<Result> = ArrayList()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        vm = ViewModelProvider(this,MenuViewModelFactory(requireActivity().applicationContext)).get(MenuVM::class.java)

        (vm as MenuVM).resultLive.observe(viewLifecycleOwner, Observer {

            resultList = it.resultList
        })

        (vm as MenuVM).massAndMpsLive.observe(viewLifecycleOwner, Observer {
            getMassAndMps = it
        })

        fun getMassAndMpsSharedPreferences() {
            (vm as MenuVM).load()
            binding.massNeedEdit.setText(getMassAndMps.mass.toString())
            binding.mpsEdit.setText(getMassAndMps.mps.toString())
        }

        fun saveMassAndMpsSharedPreferences() {
            massAndMps = MassAndMps(
                binding.massNeedEdit.text.toString().toInt(),
                binding.mpsEdit.text.toString().toInt()
            )
            (vm as MenuVM).save(massAndMps)
        }


        binding.btExpUnits.setOnClickListener{
            openExp()
        }

        binding.btResult.setOnClickListener {
            if (binding.mpsEdit.text.isNotEmpty() && binding.massNeedEdit.text.isNotEmpty()) {
                massAndMps = MassAndMps(
                    binding.massNeedEdit.text.toString().toInt(),
                    binding.mpsEdit.text.toString().toInt()
                )
                (vm as MenuVM).getResultList(massAndMps)
                getMassAndMpsSharedPreferences()

                saveMassAndMpsSharedPreferences()
                init()
            }
        }

        val liveData = findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(
            ExpFragment.MASS
        )
        liveData?.observe(viewLifecycleOwner) { mass ->
            if (mass != null) {
                binding.massNeedEdit.setText(mass)
                init()
                liveData.value = null
            }
        }
        val liveData1 = findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(
            ExpFragment.IMAGE
        )
        liveData1?.observe(viewLifecycleOwner) { image ->
            if (image != null) {
                binding.imageViewMenu.setImageResource(image)
                binding.imageViewMenu.visibility = View.VISIBLE
                liveData1.value = null
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