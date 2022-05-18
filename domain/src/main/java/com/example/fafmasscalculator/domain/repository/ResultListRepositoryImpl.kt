package com.example.fafmasscalculator.domain.repository

import com.example.fafmasscalculator.domain.models.MassAndMps
import com.example.fafmasscalculator.domain.models.Result
import com.example.fafmasscalculator.domain.models.ResultList
import kotlin.math.roundToInt

class ResultListRepositoryImpl : ResultListRepository {

    private val resultList: MutableList<Result> = ArrayList()

    override fun getResultList(massAndMps : MassAndMps): ResultList {


        var mps = massAndMps.mps
        var sec = 0
        var mass = 0
        var sacu = 0
        var best = 0.0f
        var minutes = 0.0f
        var bestResult = 0

        fun toMinutes(): Float {
            return ((((sec + (massAndMps.mass / mps)) / 60.0f)) * 100.0f).roundToInt() / 100.0f
        }

        best = toMinutes()

        fun resultAll(sacu: Int, mps: Int) {
            minutes = toMinutes()
            resultList.add(sacu, Result(sacu, mps, minutes,false))

            if (minutes < best) {
                best = minutes
                bestResult = sacu
            }
        }
        resultAll(0, mps)

        while (sec < 600) {
            sec++
            mass += mps
            if (mass >= 6450) {
                sacu++
                mps += 11
                mass -= 6450  //5320
                resultAll(sacu, mps)
            }
        }
        resultList[bestResult].best = true

        resultList.add(0, Result(0, 0, 0.0f,false))

        return ResultList(resultList)
    }
}