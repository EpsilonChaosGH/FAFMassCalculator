package com.example.fafmasscalculator.data.repository

import com.example.fafmasscalculator.data.storage.MassAndMpsStor
import com.example.fafmasscalculator.data.storage.MassAndMpsStorage
import com.example.fafmasscalculator.domain.models.MassAndMps
import com.example.fafmasscalculator.domain.repository.MassAndMpsRepository



class MassAndMpsRepositoryImpl(private val massAndMpsStorage: MassAndMpsStorage) : MassAndMpsRepository {

    override fun saveMassAndMps (saveParam : MassAndMps){
        val massAndMpsStor = MassAndMpsStor(mass = saveParam.mass, mps = saveParam.mps)
         massAndMpsStorage.save(massAndMpsStor)
    }

    override fun getMassAndMps(): MassAndMps{
        val massAndMpsStor = massAndMpsStorage.get()
        return MassAndMps(massAndMpsStor.mass,massAndMpsStor.mps)
    }
}