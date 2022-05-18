package com.example.fafmasscalculator.presentation

import androidx.lifecycle.LiveData
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

    private val massAndMpsLiveMutable = MutableLiveData<MassAndMps>()
    val massAndMpsLive: LiveData<MassAndMps> = massAndMpsLiveMutable

    private val resultLiveMutable = MutableLiveData<ResultList>()
    val resultLive: LiveData<ResultList> = resultLiveMutable

    fun save(massAndMps : MassAndMps) {
        saveMassAndMpsUseCase.execute(massAndMps)
    }

    fun load(){
        massAndMpsLiveMutable.value = getMassAndMpsUseCase.execute()
    }

    fun getResultList(massAndMps: MassAndMps){
        resultLiveMutable.value = getResultListUseCase.execute(massAndMps)
    }
}