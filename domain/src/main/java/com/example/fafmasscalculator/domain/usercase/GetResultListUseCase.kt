package com.example.fafmasscalculator.domain.usercase

import com.example.fafmasscalculator.domain.models.MassAndMps
import com.example.fafmasscalculator.domain.models.Result
import com.example.fafmasscalculator.domain.models.ResultList
import com.example.fafmasscalculator.domain.repository.ResultListRepository
import kotlin.math.roundToInt

class GetResultListUseCase(private val resultListRepository: ResultListRepository) {

    fun execute(massAndMps: MassAndMps): ResultList {
        return resultListRepository.getResultList(massAndMps)
    }
}