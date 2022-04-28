package com.example.fafmasscalculator.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fafmasscalculator.domain.models.MassAndMps
import com.example.fafmasscalculator.domain.models.ResultList
import com.example.fafmasscalculator.domain.usercase.GetMassAndMpsUseCase
import com.example.fafmasscalculator.domain.usercase.GetResultListUseCase
import com.example.fafmasscalculator.domain.usercase.SaveMassAndMpsUseCase

class MenuVM(
    private val getResultListUseCase : GetResultListUseCase,
    private val getMassAndMpsUseCase : GetMassAndMpsUseCase,
    private val saveMassAndMpsUseCase : SaveMassAndMpsUseCase
) : ViewModel() {

    var massAndMpsLive = MutableLiveData<MassAndMps>()
    var resultLive = MutableLiveData<ResultList>()

    fun save(massAndMps : MassAndMps) {
        saveMassAndMpsUseCase.execute(MassAndMps(massAndMps.mass,massAndMps.mps))
    }

    fun load(){
        massAndMpsLive.value =  getMassAndMpsUseCase.execute()
    }

    fun getResultList(massAndMps: MassAndMps){
        resultLive.value = getResultListUseCase.execute(massAndMps)
    }
}