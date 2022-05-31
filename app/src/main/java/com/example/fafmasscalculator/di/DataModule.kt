package com.example.fafmasscalculator.di

import com.example.fafmasscalculator.data.repository.ParamsRepositoryImpl
import com.example.fafmasscalculator.domain.repository.ResultListRepositoryImpl
import com.example.fafmasscalculator.data.storage.ParamsStorage
import com.example.fafmasscalculator.data.storage.SharedPrefParamsStorage
import com.example.fafmasscalculator.domain.repository.ParamsRepository
import com.example.fafmasscalculator.domain.repository.ResultListRepository
import org.koin.dsl.module


val dataModule = module {

    single<ParamsStorage> {
        SharedPrefParamsStorage(context = get())
    }

    single<ParamsRepository> {
        ParamsRepositoryImpl(paramsStorage = get())
    }

    single<ResultListRepository> {
        ResultListRepositoryImpl()
    }
}