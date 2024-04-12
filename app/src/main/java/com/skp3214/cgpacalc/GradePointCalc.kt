package com.skp3214.cgpacalc

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradePointCalc() {
    val marks = remember { mutableStateOf("") }
    val grade = remember { mutableStateOf("") }

    fun calculateGrade(marks: String):String {
        val marksInt = marks.toIntOrNull()
        return when (marksInt) {
            in 90..100 -> "10"
            in 80..90 -> "9"
            in 70..80 -> "8"
            in 60..70 -> "7"
            in 50..60 -> "6"
            in 40..50 -> "5"
            in 34..40 -> "4"
            in 0..34 -> "0"
            else -> "Invalid Input"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "CALCULATE",
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 34.sp),
            color = Color(0xFFF57A2B),
            modifier = Modifier.padding(top = 10.dp)
        )

        Text(
            text = "GRADE POINT",
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 34.sp),
            color = Color(0xFFF57A2B),
            modifier = Modifier.padding(top = 10.dp)
        )

        OutlinedTextField(
            value = marks.value,
            onValueChange = { marks.value = it },
            label = { Text(text = "Enter the Marks", color =Color(0xFFF57A2B)) },
            modifier = Modifier
                .width(250.dp)
                .padding(top = 30.dp, bottom = 20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFF57A2B),
                containerColor = Color(0xFFFCDFB4),
                unfocusedBorderColor = Color(0xFFF57A2B),
                textColor = Color(0xFFF57A2B),
            ),
            shape = RoundedCornerShape(40.dp),
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            ),
        )

        val contextForToast = LocalContext.current.applicationContext
        ElevatedButton(
            onClick = {
                val enteredMarks = marks.value.toIntOrNull()

                if (marks.value.isEmpty()) {
                    Toast.makeText(contextForToast, "Please enter the marks.", Toast.LENGTH_SHORT).show()
                } else if (enteredMarks == null) {

                    Toast.makeText(contextForToast, "Please enter numeric values for marks.", Toast.LENGTH_SHORT).show()
                } else if (enteredMarks < 0 || enteredMarks > 100) {

                    Toast.makeText(contextForToast, "Please enter marks in the range 0-100.", Toast.LENGTH_SHORT).show()
                } else {

                    grade.value=calculateGrade(marks.value)
                    Toast.makeText(contextForToast, "Your GradePoint is: ${grade.value}", Toast.LENGTH_SHORT).show()

                }

            },
            colors = ButtonDefaults.elevatedButtonColors(
                contentColor = Color(0xffd8f3dc),
                containerColor = Color(0xFFF57A2B)
            ),
            modifier = Modifier
                .width(250.dp)
                .height(70.dp)
                .padding(bottom = 20.dp)
        ) {
            Text(text = "Calculate", fontSize = 27.sp)
        }

        Text(
            text = "GRADE POINT : ${grade.value}",
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 24.sp),
            color = Color(0xFFF57A2B),
            modifier = Modifier.padding(bottom = 120.dp)
        )
    }
}
