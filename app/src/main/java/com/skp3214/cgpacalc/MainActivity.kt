package com.skp3214.cgpacalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.compose.rememberNavController

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.skp3214.cgpacalc.ui.theme.CgpaCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CgpaCalcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xffeff6e0)
                ) {
                    MyNavigation()
                }
            }
        }
    }
}

@Composable
fun MyNavigation(){
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
            ByGrade()
        }
        composable(ByNumber.route){
            ByNumber()
        }
        composable(ByGradePoint.route){
            ByGradePoint()
        }
        composable(AboutUs.route){
            AboutUs()
        }
    }

}

