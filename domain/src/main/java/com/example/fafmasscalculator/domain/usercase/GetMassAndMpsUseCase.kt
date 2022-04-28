package com.example.fafmasscalculator.domain.usercase

import com.example.fafmasscalculator.domain.models.MassAndMps
import com.example.fafmasscalculator.domain.repository.MassAndMpsRepository

class GetMassAndMpsUseCase(private val massAndMpsRepository: MassAndMpsRepository) {

    fun execute() : MassAndMps {
       return massAndMpsRepository.getMassAndMps()
    }

}