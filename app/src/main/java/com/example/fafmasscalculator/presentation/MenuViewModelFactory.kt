package com.example.fafmasscalculator.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fafmasscalculator.data.repository.MassAndMpsRepositoryImpl
import com.example.fafmasscalculator.data.repository.ResultListRepositoryImpl
import com.example.fafmasscalculator.data.storage.SharedPrefMassAndMpsStorage
import com.example.fafmasscalculator.domain.usercase.GetMassAndMpsUseCase
import com.example.fafmasscalculator.domain.usercase.GetResultListUseCase
import com.example.fafmasscalculator.domain.usercase.SaveMassAndMpsUseCase

class MenuViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val resultListRepository by lazy { ResultListRepositoryImpl() }
    private val massAndMpsRepository by lazy { MassAndMpsRepositoryImpl(SharedPrefMassAndMpsStorage(context)) }
    private val getResultListUseCase by lazy { GetResultListUseCase(resultListRepository) }
    private val getMassAndMpsUseCase by lazy { GetMassAndMpsUseCase(massAndMpsRepository) }
    private val saveMassAndMpsUseCase by lazy { SaveMassAndMpsUseCase(massAndMpsRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MenuVM(
            getResultListUseCase = getResultListUseCase,
            getMassAndMpsUseCase = getMassAndMpsUseCase,
            saveMassAndMpsUseCase = saveMassAndMpsUseCase
        ) as T
    }
}