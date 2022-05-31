package com.example.fafmasscalculator.domain.usercase

import com.example.fafmasscalculator.domain.models.Params
import com.example.fafmasscalculator.domain.models.ResultList
import com.example.fafmasscalculator.domain.repository.ResultListRepository

class GetResultListUseCase(private val resultListRepository: ResultListRepository) {

    fun execute(params: Params): ResultList {
        return resultListRepository.getResultList(params)
    }
}
