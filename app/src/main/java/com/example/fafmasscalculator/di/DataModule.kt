package com.example.fafmasscalculator.di

import com.example.fafmasscalculator.data.repository.MassAndMpsRepositoryImpl
import com.example.fafmasscalculator.domain.repository.ResultListRepositoryImpl
import com.example.fafmasscalculator.data.storage.MassAndMpsStorage
import com.example.fafmasscalculator.data.storage.SharedPrefMassAndMpsStorage
import com.example.fafmasscalculator.domain.repository.MassAndMpsRepository
import com.example.fafmasscalculator.domain.repository.ResultListRepository
import org.koin.dsl.module


val dataModule = module {

    single<MassAndMpsStorage> {
        SharedPrefMassAndMpsStorage(context = get())
    }

    single<MassAndMpsRepository> {
        MassAndMpsRepositoryImpl(massAndMpsStorage = get())
    }

    single<ResultListRepository> {
        ResultListRepositoryImpl()
    }
}