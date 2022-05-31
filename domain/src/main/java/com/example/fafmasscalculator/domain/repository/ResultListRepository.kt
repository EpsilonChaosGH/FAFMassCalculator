package com.example.fafmasscalculator.domain.repository

import com.example.fafmasscalculator.domain.models.Params
import com.example.fafmasscalculator.domain.models.ResultList

interface ResultListRepository {

    fun getResultList(params: Params): ResultList
}