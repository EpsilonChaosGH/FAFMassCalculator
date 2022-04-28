package com.example.fafmasscalculator.domain.repository

import com.example.fafmasscalculator.domain.models.MassAndMps

interface MassAndMpsRepository {

    fun saveMassAndMps(saveParam : MassAndMps)

    fun getMassAndMps() : MassAndMps
}