package com.skp3214.cgpacalc.utils

import com.skp3214.cgpacalc.mvi.CGPACalcViewState


fun updateGrade(currentState: CGPACalcViewState.Success, index: Int, grade: String): CGPACalcViewState.Success {
    val newGrades = currentState.gradesValues.toMutableList().apply { this[index] = grade }
    return currentState.copy(gradesValues = newGrades)
}

fun updateCredit(currentState: CGPACalcViewState.Success, index: Int, credit: Int): CGPACalcViewState.Success {
    val newCredits = currentState.creditValues.toMutableList().apply { this[index] = credit }
    return currentState.copy(creditValues = newCredits)
}

fun calculateCGPA(currentState: CGPACalcViewState.Success, calculationType: CalculationType): CGPACalcViewState.Success {
    val gradepoints = when (calculationType) {
        CalculationType.ByGrade -> currentState.gradesValues.map { gradeToPoint(it) }
        CalculationType.ByGradePoint -> currentState.gradesValues.mapNotNull { it.toDoubleOrNull() }
        CalculationType.ByNumber -> currentState.gradesValues.map { markToPoint(it) }
    }

    val credits = currentState.creditValues.mapNotNull { it }

    val validGradesAndCredits = gradepoints.zip(credits).filter { (_, credit) -> credit > 0 }

    val totalCredits = validGradesAndCredits.sumOf { (_, credit) -> credit }

    val totalGradePoints = validGradesAndCredits.sumOf { (gradepoint, credit) -> gradepoint * credit }

    val cgpa = if (totalCredits > 0) totalGradePoints / totalCredits else 0.0

    return currentState.copy(cgpa = cgpa, calculationType = calculationType)
}
