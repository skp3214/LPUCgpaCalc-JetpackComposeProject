package com.skp3214.cgpacalc.architecture

import com.skp3214.cgpacalc.utils.CalculationType

sealed class CGPACalcViewState{
    data class Success(
        val gradesValues:List<String> = List(12){""},
        val creditValues:List<Int?> = List(12){0},
        val cgpa:Double=0.0,
        val calculationType:CalculationType
    ):CGPACalcViewState()
}

