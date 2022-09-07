package com.example.fafmasscalculator.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fafmasscalculator.domain.models.Params
import com.example.fafmasscalculator.domain.models.ResultList
import com.example.fafmasscalculator.domain.usercase.*

class MenuVM(
    private val getResultListUseCase: GetResultListUseCase,
    private val getParamsUseCase: GetParamsUseCase,
    private val saveParamsUseCase: SaveParamsUseCase,
    private val resultsServices: ResultsServices,
) : ViewModel() {

    private val _params = MutableLiveData<Params>()
    val params: LiveData<Params> = _params

    private val _result = MutableLiveData<ResultList>()
    val result: LiveData<ResultList> = _result

    private val listener: ResultListener = {
        _result.value = it
    }

    init {
        resultsServices.addListener(listener)
        load()
    }

    private fun load() {
        _params.value = getParamsUseCase.execute()
    }

    fun save(params: Params) {
        saveParamsUseCase.execute(params)
    }

    fun getResultList(params: Params) {
        resultsServices.addResult(getResultListUseCase.execute(params))
        resultsServices.notifyChanges()
    }

    override fun onCleared() {
        super.onCleared()
        resultsServices.removeListeners(listener)
    }
}