package com.example.fafmasscalculator.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Fragment.collectFlow(flow: Flow<T>, onCollect: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect {
                onCollect(it)
            }
        }
    }
}
//class MenuViewModelFactory(context: Context) : ViewModelProvider.Factory {
//
//    private val resultListRepository by lazy { ResultListRepositoryImpl() }
//    private val massAndMpsRepository by lazy { MassAndMpsRepositoryImpl(SharedPrefMassAndMpsStorage(context)) }
//    private val getResultListUseCase by lazy { GetResultListUseCase(resultListRepository) }
//    private val getMassAndMpsUseCase by lazy { GetMassAndMpsUseCase(massAndMpsRepository) }
//    private val saveMassAndMpsUseCase by lazy { SaveMassAndMpsUseCase(massAndMpsRepository) }
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return MenuVM(
//            getResultListUseCase = getResultListUseCase,
//            getMassAndMpsUseCase = getMassAndMpsUseCase,
//            saveMassAndMpsUseCase = saveMassAndMpsUseCase
//        ) as T
//    }
//}

///////////////


//fun main1() {
//    var numbers = arrayOf(11, 15, 1, 7, 6, 22, 28, 61, 5, 23)
//
//    var isSorted = false
//
//    while (!isSorted) {
//        isSorted = true
//        for (i in 1 until numbers.size) {
//            if (numbers[i] < numbers[i - 1]) {
//                val x = numbers[i]
//                numbers[i] = numbers[i - 1]
//                numbers[i - 1] = x
//                isSorted = false
//            }
//        }
//    }
//    println(numbers.contentToString())
//}


/////////////////

//class ResultListRepositoryImpl : ResultListRepository {
//
//    private val resultList: MutableList<Result> = ArrayList()
//
//    override fun getResultList(massAndMps: MassAndMps): ResultList {
//
//
//        var mps = massAndMps.mps
//        var sec = 0
//        var mass = 0
//        var sacu = 0
//        var best: Float
//        var minutes: Float
//        var bestResult = 0
//
//        fun toMinutes(): Float {
//            return ((((sec + (massAndMps.mass / mps)) / 60.0f)) * 100.0f).roundToInt() / 100.0f
//        }
//
//        best = toMinutes()
//
//        fun resultAll(sacu: Int, mps: Int) {
//            minutes = toMinutes()
//            resultList.add(sacu, Result(sacu, mps, minutes, false))
//
//            if (minutes < best) {
//                best = minutes
//                bestResult = sacu
//            }
//        }
//        resultAll(0, mps)
//
//        while (sec < 600) {
//            sec++
//            mass += mps
//            if (mass >= 6450) {
//                sacu++
//                mps += 11
//                mass -= 6450  //5320
//                resultAll(sacu, mps)
//            }
//        }
//        resultList[bestResult].best = true
//
//        resultList.add(0, Result(0, 0, 0.0f, false))
//
//        return ResultList(resultList)
//    }
//}

