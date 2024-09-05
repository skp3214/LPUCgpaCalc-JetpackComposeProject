package com.skp3214.cgpacalc.utils

fun markToPoint(mark: String): Double {
    val marksInt = mark.toIntOrNull()
    return when (marksInt) {
        in 90..100 -> 10.0
        in 80 until 90 -> 9.0
        in 70 until 80 -> 8.0
        in 60 until 70 -> 7.0
        in 50 until 60 -> 6.0
        in 40 until 50 -> 5.0
        in 34 until 40 -> 4.0
        in 0 until 34 -> 0.0
        else -> 0.0
    }
}

fun gradeToPoint(grade: String): Double {
    return when (grade.uppercase()) {
        "O" -> 10.0
        "A+" -> 9.0
        "A" -> 8.0
        "B+" -> 7.0
        "B" -> 6.0
        "C" -> 5.0
        "D" -> 4.0
        "E", "R" -> 0.0
        else -> 0.0
    }
}
