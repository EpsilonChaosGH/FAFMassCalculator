package com.example.fafmasscalculator.data.repository

import com.example.fafmasscalculator.data.storage.ParamsStorage
import com.example.fafmasscalculator.domain.models.Params
import com.example.fafmasscalculator.domain.repository.ParamsRepository


class ParamsRepositoryImpl(private val paramsStorage: ParamsStorage) : ParamsRepository {

    override fun saveParams(saveParam: Params) {
        val paramsData =
            com.example.fafmasscalculator.data.storage.ParamsData(
                massCost = saveParam.massCost,
                massIncome = saveParam.massIncome
            )
        paramsStorage.save(paramsData)
    }

    override fun getParams(): Params {
        val massAndMpsStor = paramsStorage.get()
        return Params(massCost = massAndMpsStor.massCost, massIncome = massAndMpsStor.massIncome)
    }
}