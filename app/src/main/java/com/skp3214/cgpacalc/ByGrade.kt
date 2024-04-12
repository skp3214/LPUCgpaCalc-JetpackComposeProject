package com.skp3214.cgpacalc

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ByGrade() {
    val numberOfFields = 12
    val gradeValues = remember { mutableStateListOf<String>() }
    val creditValues = remember { mutableStateListOf<String>() }
    var cgpa by remember { mutableDoubleStateOf(0.0) }

    repeat(numberOfFields) {
        gradeValues.add(it, "")
        creditValues.add(it, "")
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
            "E" -> 0.0
            "R"->0.0
            else -> 0.0
        }
    }
    fun isValidGrade(grade: String): Boolean {
        return when (grade.uppercase()) {
            "O", "A+", "A", "B+", "B", "C", "D", "E", "R" -> true
            else -> false
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
            text = "Your CGPA : ${"%.2f".format(cgpa)}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFFF57A2B)
        )

        Row(modifier = Modifier.fillMaxWidth().padding(6.dp)) {
            Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                Text(
                    text = "Enter Grade",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 35.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF57A2B)
                )
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Enter Credit",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 35.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF57A2B)
                )
            }
        }

        LazyColumn(modifier = Modifier.fillMaxHeight(0.83f)) {

            items(numberOfFields) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = gradeValues[index],
                        onValueChange = { value ->
                            gradeValues[index]=value
                        },
                        label = { Text("Subject ${index + 1}: Grade ", fontSize = 9.sp ,color = Color(0xFFF57A2B)) },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(start = 10.dp, end = 5.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFF57A2B),
                            containerColor = Color(0xFFFCDFB4),
                            unfocusedBorderColor = Color(0xFFF57A2B),
                            textColor = Color(0xFFF57A2B),
                        ),
                        shape = RoundedCornerShape(5.dp),
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp
                        ),
                    )
                    OutlinedTextField(
                        value = creditValues[index],
                        onValueChange = { value ->
                            creditValues[index]=value
                        },
                        label = { Text("Subject Credit", fontSize = 9.sp ,color = Color(0xFFF57A2B)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, end = 10.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFF57A2B),
                            containerColor = Color(0xFFFCDFB4),
                            unfocusedBorderColor = Color(0xFFF57A2B),
                            textColor = Color(0xFFF57A2B),
                        ),
                        shape = RoundedCornerShape(5.dp),
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp
                        ),
                    )
                }
            }
        }
        val contextForToast = LocalContext.current.applicationContext
        Row(modifier = Modifier.padding(top = 25.dp)) {
            ElevatedButton(
                onClick = {
                    var totalGrade = 0.0
                    var totalCredit = 0
                    var validInput = true

                    for (i in 0 until numberOfFields) {
                        val grade = gradeValues[i]
                        val credit = creditValues[i]

                        // Validate grade input
                        if (grade.isNotBlank()) {
                            if (!isValidGrade(grade)) {
                                validInput = false
                                Toast.makeText(contextForToast, "Enter only valid grade for Subject ${i + 1}", Toast.LENGTH_LONG).show()
                                break
                            }
                        }

                        // Validate credit input
                        if (credit.isNotBlank()) {
                            if (!credit.matches("\\d+".toRegex())) {
                                validInput = false
                                Toast.makeText(contextForToast, "Enter only numbers for Subject ${i + 1} credit", Toast.LENGTH_LONG).show()
                                break
                            }
                        }

                        if (grade.isNotBlank() && credit.isNotBlank()) {
                            totalGrade += gradeToPoint(grade) * credit.toInt()
                            totalCredit += credit.toInt()
                        }
                    }

                    if (validInput) {
                        cgpa = if (totalCredit != 0) totalGrade / totalCredit else 0.0
                        Toast.makeText(contextForToast, "Your CGPA: ${"%.2f".format(cgpa)}", Toast.LENGTH_LONG).show()
                    }

                },
                colors = ButtonDefaults.elevatedButtonColors(
                    contentColor = Color(0xffd8f3dc),
                    containerColor = Color(0xFFF57A2B)
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(44.dp)
            ) {
                Text(text = "Calculate CGPA", fontSize = 20.sp)
            }
        }
    }
}
