package com.example.fafmasscalculator.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fafmasscalculator.domain.models.Params
import com.example.fafmasscalculator.domain.models.ResultList
import com.example.fafmasscalculator.domain.usercase.GetParamsUseCase
import com.example.fafmasscalculator.domain.usercase.GetResultListUseCase
import com.example.fafmasscalculator.domain.usercase.SaveParamsUseCase

class MenuVM(
    private val getResultListUseCase: GetResultListUseCase,
    private val getParamsUseCase: GetParamsUseCase,
    private val saveParamsUseCase: SaveParamsUseCase
) : ViewModel() {

    private val paramsLiveMutable = MutableLiveData<Params>()
    val paramsLive: LiveData<Params> = paramsLiveMutable

    private val resultLiveMutable = MutableLiveData<ResultList>()
    val resultLive: LiveData<ResultList> = resultLiveMutable

    fun save(params: Params) {
        saveParamsUseCase.execute(params)
    }

    fun load() {
        paramsLiveMutable.value = getParamsUseCase.execute()
    }

    fun getResultList(params: Params) {
        resultLiveMutable.value = getResultListUseCase.execute(params)
    }
}