package com.skp3214.cgpacalc.architecture

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.skp3214.cgpacalc.utils.updateCredit
import com.skp3214.cgpacalc.utils.updateGrade
import com.skp3214.cgpacalc.utils.calculateCGPA
import com.skp3214.cgpacalc.utils.CalculationType

class CGPACalcViewModel : ViewModel() {
    private val _state = mutableStateOf<CGPACalcViewState>(CGPACalcViewState.Success(
        gradesValues = List(12) { "" },
        creditValues = List(12) { 0 },
        cgpa = 0.0,
        calculationType = CalculationType.ByGrade
    ))

    val state: State<CGPACalcViewState> = _state

    fun processIntent(intent: CGPACalcViewIntent) {
        when (intent) {
            is CGPACalcViewIntent.SetGrade -> updateGradeInViewModel(intent.index, intent.grade)
            is CGPACalcViewIntent.SetCredit -> updateCreditInViewModel(intent.index, intent.credit)
            is CGPACalcViewIntent.CalculateCgpa -> calculateCGPAInViewModel(intent.calculationType)
            is CGPACalcViewIntent.ClearState -> clearState()
        }
    }

    private fun updateGradeInViewModel(index: Int, grade: String) {
        val currentState = _state.value as CGPACalcViewState.Success
        updateState(updateGrade(currentState, index, grade))
    }

    private fun updateCreditInViewModel(index: Int, credit: Int) {
        val currentState = _state.value as CGPACalcViewState.Success
        updateState(updateCredit(currentState, index, credit))
    }

    private fun calculateCGPAInViewModel(calculationType: CalculationType) {
        val currentState = _state.value as CGPACalcViewState.Success
        updateState(calculateCGPA(currentState, calculationType))
    }

    private fun clearState() {
        _state.value = CGPACalcViewState.Success(
            gradesValues = List(12) { "" },
            creditValues = List(12) { 0 },
            cgpa = 0.0,
            calculationType = CalculationType.ByGrade
        )
    }

    private fun updateState(newState: CGPACalcViewState) {
        _state.value = newState
    }
}
