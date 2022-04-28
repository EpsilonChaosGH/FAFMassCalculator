package com.example.fafmasscalculator.data.storage

import com.example.fafmasscalculator.domain.models.MassAndMps

interface MassAndMpsStorage {

    fun save(saveParam : MassAndMpsStor)

    fun get() : MassAndMpsStor

}