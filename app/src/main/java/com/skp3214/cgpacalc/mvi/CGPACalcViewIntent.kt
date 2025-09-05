package com.skp3214.cgpacalc.mvi

import com.skp3214.cgpacalc.utils.CalculationType

sealed class CGPACalcViewIntent{
    data class SetGrade(val index:Int,val grade:String):CGPACalcViewIntent()
    data class SetCredit(val index:Int,val credit:Int):CGPACalcViewIntent()
    data class CalculateCgpa(val calculationType:CalculationType):CGPACalcViewIntent()
    object ClearState : CGPACalcViewIntent()
}