package com.example.fafmasscalculator.domain.usercase

import com.example.fafmasscalculator.domain.models.Params
import com.example.fafmasscalculator.domain.repository.ParamsRepository

class GetParamsUseCase(private val paramsRepository: ParamsRepository) {

    fun execute(): Params {
        return paramsRepository.getParams()
    }

}