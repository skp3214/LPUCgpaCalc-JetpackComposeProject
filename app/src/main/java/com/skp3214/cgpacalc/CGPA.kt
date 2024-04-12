package com.skp3214.cgpacalc


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CGPA(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Calculate",
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 54.sp),
            color = Color(0xffe85d04),
            modifier = Modifier.padding(top = 10.dp)
        )

        Text(
            text = "C.G.P.A",
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold,
            style = TextStyle(fontSize = 44.sp),
            color = Color(0xffe85d04),
            modifier = Modifier.padding(top = 10.dp)
        )



        ElevatedButton(
            onClick = { navController.navigate(ByNumber.route) },
            colors = ButtonDefaults.elevatedButtonColors(
                contentColor = Color(0xffeff6e0),
                containerColor = Color(0xFFF57A2B)
            ),
            modifier = Modifier
                .padding(top = 80.dp).width(190.dp)
                .fillMaxWidth()
        ) {
            Text(text = "By Marks")
        }

        ElevatedButton(
            onClick = { navController.navigate(ByGradePoint.route) },
            colors = ButtonDefaults.elevatedButtonColors(
                contentColor = Color(0xffeff6e0),
                containerColor = Color(0xFFF57A2B)
            ),
            modifier = Modifier
                .padding(top = 20.dp)
                .width(190.dp)
        ) {
            Text(text = "By GradePoint")
        }

        ElevatedButton(
            onClick = { navController.navigate(ByGrade.route) },
            colors = ButtonDefaults.elevatedButtonColors(
                contentColor = Color(0xffeff6e0),
                containerColor = Color(0xFFF57A2B)
            ),
            modifier = Modifier
                .padding(top = 20.dp)
                .width(190.dp)
        ) {
            Text(text = "By Grade")
        }

    }
}


