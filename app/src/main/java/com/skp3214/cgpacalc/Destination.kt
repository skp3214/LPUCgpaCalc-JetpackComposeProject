package com.skp3214.cgpacalc

interface Destination {
    val route:String
}

object OnBoard:Destination{
    override val route="OnBoard"
}

object CGPA:Destination{
    override val route="CGPA"
}

object GradCalc:Destination{
    override val route="GradeCalc"
}

object GradePointCalc:Destination{
    override val route="GradePointCalc"
}

object MinimumMark:Destination{
    override val route="MinimumMark"
}

object ByNumber:Destination{
    override val route="ByNumber"
}

object ByGrade:Destination{
    override val route="ByGrade"
}
object ByGradePoint:Destination{
    override val route="ByGradePoint"
}

object  AboutUs:Destination{
    override val route="AboutUs"
}
