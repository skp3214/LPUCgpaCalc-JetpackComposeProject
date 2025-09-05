package com.skp3214.cgpacalc.view

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skp3214.cgpacalc.mvi.CGPACalcViewModel
import com.skp3214.cgpacalc.mvi.CGPACalcViewIntent
import com.skp3214.cgpacalc.mvi.CGPACalcViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradePointCalc(viewModel: CGPACalcViewModel = viewModel()) {
    val state = viewModel.state.value as CGPACalcViewState.Success
    val contextForToast = LocalContext.current

    // Use the first grade value for single calculation
    val marks = state.gradesValues[0]
    val calculatedGradePoint = when (marks.toIntOrNull()) {
        null -> ""
        in 90..100 -> "10"
        in 80 until 90 -> "9"
        in 70 until 80 -> "8"
        in 60 until 70 -> "7"
        in 50 until 60 -> "6"
        in 40 until 50 -> "5"
        in 34 until 40 -> "4"
        in 0 until 34 -> "0"
        else -> "Invalid Input"
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
            value = marks,
            onValueChange = { value ->
                viewModel.processIntent(CGPACalcViewIntent.SetGrade(0, value))
            },
            label = { Text(text = "Enter the Marks", color = Color(0xFFF57A2B)) },
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

        ElevatedButton(
            onClick = {
                val enteredMarks = marks.toIntOrNull()

                if (marks.isEmpty()) {
                    Toast.makeText(contextForToast, "Please enter the marks.", Toast.LENGTH_SHORT).show()
                } else if (enteredMarks == null) {
                    Toast.makeText(contextForToast, "Please enter numeric values for marks.", Toast.LENGTH_SHORT).show()
                } else if (enteredMarks < 0 || enteredMarks > 100) {
                    Toast.makeText(contextForToast, "Please enter marks in the range 0-100.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(contextForToast, "Your Grade Point is: $calculatedGradePoint", Toast.LENGTH_SHORT).show()
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
            text = "GRADE POINT : $calculatedGradePoint",
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 24.sp),
            color = Color(0xFFF57A2B),
            modifier = Modifier.padding(bottom = 120.dp)
        )
    }
}
