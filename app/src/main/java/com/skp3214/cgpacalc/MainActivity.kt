package com.skp3214.cgpacalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.compose.rememberNavController

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.skp3214.cgpacalc.architecture.CGPACalcViewModel
import com.skp3214.cgpacalc.architecture.CGPACalcViewState
import com.skp3214.cgpacalc.ui.theme.CgpaCalcTheme
import com.skp3214.cgpacalc.view.AboutUs
import com.skp3214.cgpacalc.view.ByGrade
import com.skp3214.cgpacalc.view.ByGradePoint
import com.skp3214.cgpacalc.view.ByNumber
import com.skp3214.cgpacalc.view.CGPA
import com.skp3214.cgpacalc.view.GradCalc
import com.skp3214.cgpacalc.view.GradeCalc
import com.skp3214.cgpacalc.view.GradePointCalc
import com.skp3214.cgpacalc.view.MinimumMark
import com.skp3214.cgpacalc.view.OnBoard

class MainActivity : ComponentActivity() {
    private val cgpaCalcViewModel:CGPACalcViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CgpaCalcTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xffeff6e0)
                ) {
                    val state by cgpaCalcViewModel.state
                    when(state){
                        is CGPACalcViewState.Success-> {MyNavigation(cgpaCalcViewModel = cgpaCalcViewModel)}
                    }

                }
            }
        }
    }
}

@Composable
fun MyNavigation(cgpaCalcViewModel: CGPACalcViewModel){
    val navController=rememberNavController()
    NavHost(navController = navController, startDestination = OnBoard.route){
        composable(OnBoard.route){
            OnBoard(navController=navController)
        }
        composable(GradCalc.route){
            GradeCalc()
        }
        composable(GradePointCalc.route){
            GradePointCalc()
        }
        composable(MinimumMark.route){
            MinimumMark()
        }
        composable(CGPA.route){
            CGPA(navController=navController)
        }
        composable(ByGrade.route){
            ByGrade(cgpaCalcViewModel=cgpaCalcViewModel)
        }
        composable(ByNumber.route){
            ByNumber(cgpaCalcViewModel=cgpaCalcViewModel)
        }
        composable(ByGradePoint.route){
            ByGradePoint()
        }
        composable(AboutUs.route){
            AboutUs()
        }
    }

}

