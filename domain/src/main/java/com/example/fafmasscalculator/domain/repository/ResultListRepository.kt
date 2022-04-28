package com.example.fafmasscalculator.domain.repository

import com.example.fafmasscalculator.domain.models.MassAndMps
import com.example.fafmasscalculator.domain.models.ResultList

interface ResultListRepository {

    fun getResultList(massAndMps : MassAndMps): ResultList
}