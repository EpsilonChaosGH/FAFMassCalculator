package com.example.fafmasscalculator.domain.repository

import com.example.fafmasscalculator.domain.models.Params

interface ParamsRepository {

    fun saveParams(saveParam: Params)

    fun getParams(): Params
}