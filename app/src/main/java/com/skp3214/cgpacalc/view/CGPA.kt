package com.skp3214.cgpacalc.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.skp3214.cgpacalc.R


@Composable
fun CGPA(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFF8F8FF), Color(0xFFF57A2B)),
                    startY = 0f,
                    endY = 1200f,
                    tileMode = TileMode.Clamp
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
            }
            Surface(
                shape = RoundedCornerShape(32.dp),
                shadowElevation = 8.dp,
                tonalElevation = 4.dp,
                color = Color.White.copy(alpha = 0.95f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val imageModifier = Modifier.size(160.dp)
                    Image(
                        painter = painterResource(id = R.drawable.lpulogo),
                        contentDescription = "Lpu Logo",
                        contentScale = ContentScale.Fit,
                        modifier = imageModifier
                    )
                    Text(
                        text = "Calculate",
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.ExtraBold,
                        style = TextStyle(fontSize = 44.sp),
                        color = Color(0xffe85d04),
                        modifier = Modifier.padding(top = 30.dp)
                    )
                    Text(
                        text = "CGPA",
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 54.sp),
                        color = Color(0xffe85d04),
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    ElevatedButton(
                        onClick = { navController.navigate(ByGrade.route) },
                        colors = ButtonDefaults.elevatedButtonColors(
                            contentColor = Color(0xffeff6e0),
                            containerColor = Color(0xFFF57A2B)
                        ),
                        modifier = Modifier
                            .width(190.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "BY GRADE", fontWeight = FontWeight.Bold, fontSize = 18.sp)
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
                        Text(text = "BY GRADEPOINT", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    ElevatedButton(
                        onClick = { navController.navigate(ByNumber.route) },
                        colors = ButtonDefaults.elevatedButtonColors(
                            contentColor = Color(0xffeff6e0),
                            containerColor = Color(0xFFF57A2B)
                        ),
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .width(190.dp)
                    ) {
                        Text(text = "BY NUMBER", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}