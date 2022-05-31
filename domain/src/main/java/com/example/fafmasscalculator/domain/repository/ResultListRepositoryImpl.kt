package com.example.fafmasscalculator.domain.repository

import com.example.fafmasscalculator.domain.models.Params
import com.example.fafmasscalculator.domain.models.Result
import com.example.fafmasscalculator.domain.models.ResultList
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class ResultListRepositoryImpl : ResultListRepository {

    private val resultList: MutableList<Result> = ArrayList()

    override fun getResultList(params: Params): ResultList {
        var massCost = params.massCost
        var massIncome = params.massIncome
        var massCurrent = 0
        var sec = 0.0f
        var secMax = 600.0f
        var sacu = 0
        var sacuCost = 6450  //5320
        var sacuIncome = 11
        var bestResult = 0

        fun toMinutes(): Float {
            return ((((sec + (massCost / massIncome)) / 60.0f)) * 100.0f).roundToInt() / 100.0f
        }

        var best = toMinutes()

        fun resultAll(sacu: Int, massIncome: Int) {
            resultList.add(sacu, Result(sacu, massIncome, toMinutes(), false))
            if (toMinutes() < best) {
                best = toMinutes()
                bestResult = sacu
            }
        }

        resultAll(0, massIncome)

        while (sec < secMax) {
            massCurrent += massIncome
            sec++
            if (massCurrent >= sacuCost) {
                sacu++
                massIncome += sacuIncome
                massCurrent -= sacuCost
                resultAll(sacu, massIncome)
            }
        }

        resultList[bestResult].best = true
        resultList.add(0, Result(0, 0, 0.0f, false))

        return ResultList(resultList)
    }
}