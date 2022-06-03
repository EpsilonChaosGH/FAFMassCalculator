package com.example.fafmasscalculator.presentation.screens

import android.app.Activity
import android.content.Context
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
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
import kotlinx.android.synthetic.main.fragment_menu.view.*
import kotlinx.android.synthetic.main.result_item.view.*
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
    }

    override fun onResume() {
        super.onResume()
        binding.imageViewMenu.setOnClickListener {
            openExp()
        }

        binding.editTextMassCost.addTextChangedListener(textWatcher)
        binding.editTextMassIncome.addTextChangedListener(textWatcher)

        updateResult()
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (binding.editTextMassIncome.text.isNotEmpty() && binding.editTextMassCost.text.isNotEmpty()) updateResult()
        }
    }

    private fun updateResult() {
        resultList.clear()
        vm.getResultList(currentParams())
        vm.save(currentParams())
        init()
    }

    private fun currentParams(): Params {
        params = Params(
            massCost = binding.editTextMassCost.text.toString().toInt(),
            massIncome = binding.editTextMassIncome.text.toString().toInt()
        )
        return params
    }

    private fun init() {
        binding.apply {
            rcViewResult.layoutManager = GridLayoutManager(activity, 1)
            rcViewResult.adapter = adapter
            adapter.addAll(resultList)
        }
    }

    private fun openExp() {
        activity?.let { hideKeyboardFrom(it, view) }
        val direction = MenuFragmentDirections.actionMenuFragmentToExpFragment()
        findNavController().navigate(direction)

    }

    private fun hideKeyboardFrom(context: Context, view: View?) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}