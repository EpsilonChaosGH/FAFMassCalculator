package com.example.fafmasscalculator.presentation

import androidx.lifecycle.ViewModel
import com.example.fafmasscalculator.domain.models.Params
import com.example.fafmasscalculator.domain.models.ResultList
import com.example.fafmasscalculator.domain.usercase.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine

class MenuVM(
    private val getResultListUseCase: GetResultListUseCase,
    private val getParamsUseCase: GetParamsUseCase,
    private val saveParamsUseCase: SaveParamsUseCase,
) : ViewModel() {

    private val _params = MutableStateFlow<Params>(getParamsUseCase.execute())
    val params: StateFlow<Params> = _params

    private val _result = MutableStateFlow<ResultList>(getResultListUseCase.execute(_params.value))
    val result: StateFlow<ResultList> = _result

    init {
        loadParams()
        getResultList(_params.value)
    }

    private fun loadParams() {
        _params.value = getParamsUseCase.execute()
    }

    fun save(params: Params) {
        _params.value = params
        saveParamsUseCase.execute(params)
    }

    fun getResultList(params: Params) {
        _result.value = getResultListUseCase.execute(params)
    }

}