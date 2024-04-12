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
import androidx.compose.runtime.Composable
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
fun MinimumMark() {

    val marks = remember { mutableStateOf("") }
    val grade = remember { mutableStateOf("") }

    fun calculateMinimumMarks(marks: String): String {
        return when (marks.uppercase()) {
            "O" -> "90"
            "A+" -> "80"
            "A" -> "70"
            "B+" -> "60"
            "B" -> "50"
            "C" -> "40"
            "D" -> "34"
            "E" -> "0"
            "R"-> "0"
            else -> "fail" // Handle other cases or invalid input
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
            text = "MINIMUM MARKS",
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 34.sp),
            color = Color(0xFFF57A2B),
            modifier = Modifier.padding(top = 10.dp,bottom = 20.dp)
        )

        OutlinedTextField(
            value = marks.value,
            onValueChange = { marks.value = it },
            label = { Text(text = "Enter the Grade", color =  Color(0xFFF57A2B)) },
            modifier = Modifier
                .width(250.dp)
                .padding(bottom = 20.dp),
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
                val enteredMarks = marks.value.uppercase()

                // Validate input
                val validGrades = setOf("O", "A+", "A", "B+", "B", "C", "D", "E","R")

                if (enteredMarks !in validGrades) {
                    // Invalid input, display a toast message
                    Toast.makeText(contextForToast, "Please enter only valid grades (O, A+, A, B+, B, C, D, E, R)", Toast.LENGTH_SHORT).show()
                }
                else {
                    // Valid input, proceed with calculation
                    grade.value=calculateMinimumMarks(enteredMarks)
                    Toast.makeText(contextForToast, "Min marks needed is: ${grade.value}", Toast.LENGTH_SHORT).show()

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
            text = "MIN MARKS NEEDED : ",
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 24.sp),
            color = Color(0xFFF57A2B),
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(
            text = grade.value,
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 34.sp),
            color = Color(0xFFF57A2B),
            modifier = Modifier.padding(bottom = 130.dp)
        )
    }
}
