package com.example.fafmasscalculator.presentation.screens

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fafmasscalculator.ExpParcelable
import com.example.fafmasscalculator.R
import com.example.fafmasscalculator.ResultAdapter
import com.example.fafmasscalculator.databinding.FragmentMenuBinding
import com.example.fafmasscalculator.domain.models.Params

import com.example.fafmasscalculator.presentation.MenuVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var params: Params
    private val viewModel by viewModel<MenuVM>()
    private val adapter = ResultAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        binding.rcViewResult.layoutManager = LinearLayoutManager(activity)
        binding.rcViewResult.adapter = adapter

        viewModel.result.observe(viewLifecycleOwner) {
            adapter.resultList = it.resultList
        }

        viewModel.params.observe(viewLifecycleOwner) {
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
            if (binding.editTextMassIncome.text.isNotEmpty() && binding.editTextMassCost.text.isNotEmpty()) {
             if (binding.editTextMassIncome.text.toString().toInt() > 0 && binding.editTextMassCost.text.toString().toInt() > 0)  updateResult()
            }
        }
    }

    private fun updateResult() {
        viewModel.getResultList(currentParams())
        viewModel.save(currentParams())

    }

    private fun currentParams(): Params {
        params = Params(
            massCost = binding.editTextMassCost.text.toString().toInt(),
            massIncome = binding.editTextMassIncome.text.toString().toInt()
        )
        return params
    }

    private fun openExp() {
        activity?.let { hideKeyboardFrom(it, view) }
        val direction = MenuFragmentDirections.actionMenuFragmentToExpFragment()
        findNavController().navigate(direction)
    }

    private fun hideKeyboardFrom(context: Context, view: View?) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}