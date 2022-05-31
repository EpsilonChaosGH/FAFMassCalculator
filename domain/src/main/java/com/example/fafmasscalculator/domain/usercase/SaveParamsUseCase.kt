package com.example.fafmasscalculator.domain.usercase

import com.example.fafmasscalculator.domain.models.Params
import com.example.fafmasscalculator.domain.repository.ParamsRepository

class SaveParamsUseCase(private val paramsRepository: ParamsRepository) {

    fun execute(param: Params) {
        paramsRepository.saveParams(param)
    }
}