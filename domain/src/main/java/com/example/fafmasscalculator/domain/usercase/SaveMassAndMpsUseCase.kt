package com.example.fafmasscalculator.domain.usercase

import com.example.fafmasscalculator.domain.models.MassAndMps
import com.example.fafmasscalculator.domain.repository.MassAndMpsRepository

class SaveMassAndMpsUseCase(private val massAndMpsRepository: MassAndMpsRepository) {

    fun execute(param : MassAndMps){
        massAndMpsRepository.saveMassAndMps(param)
    }
}