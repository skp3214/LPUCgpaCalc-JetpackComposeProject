package com.skp3214.cgpacalc.view

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skp3214.cgpacalc.architecture.CGPACalcViewIntent
import com.skp3214.cgpacalc.architecture.CGPACalcViewModel
import com.skp3214.cgpacalc.architecture.CGPACalcViewState
import com.skp3214.cgpacalc.utils.CalculationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ByGrade(cgpaCalcViewModel: CGPACalcViewModel = viewModel()) {
    val state = cgpaCalcViewModel.state.value as CGPACalcViewState.Success
    val contextForToast = LocalContext.current

    // Clear state and set calculation type when this screen is first loaded
    LaunchedEffect(Unit) {
        cgpaCalcViewModel.processIntent(CGPACalcViewIntent.ClearState)
        if (state.calculationType != CalculationType.ByGrade) {
            cgpaCalcViewModel.processIntent(CGPACalcViewIntent.CalculateCgpa(CalculationType.ByGrade))
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
            text = "Your CGPA : ${"%.2f".format(state.cgpa)}",
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
            items(state.gradesValues.size) { index ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = state.gradesValues[index],
                        onValueChange = { value ->
                            cgpaCalcViewModel.processIntent(CGPACalcViewIntent.SetGrade(index, value))
                        },
                        label = { Text("Subject ${index + 1}: Grade ", fontSize = 9.sp, color = Color(0xFFF57A2B)) },
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
                        value = state.creditValues[index]?.toString() ?: "",
                        onValueChange = { value ->
                            val credit = value.toIntOrNull() ?: 0
                            cgpaCalcViewModel.processIntent(CGPACalcViewIntent.SetCredit(index, credit))
                        },
                        label = { Text("Subject Credit", fontSize = 9.sp, color = Color(0xFFF57A2B)) },
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

        Row(modifier = Modifier.padding(top = 25.dp)) {
            ElevatedButton(
                onClick = {
                    cgpaCalcViewModel.processIntent(CGPACalcViewIntent.CalculateCgpa(CalculationType.ByGrade))
                    Toast.makeText(contextForToast, "Your CGPA: ${"%.2f".format(state.cgpa)}", Toast.LENGTH_LONG).show()
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
