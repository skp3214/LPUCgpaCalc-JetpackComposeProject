package com.skp3214.cgpacalc.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.skp3214.cgpacalc.mvi.CGPACalcViewModel
import com.skp3214.cgpacalc.mvi.CGPACalcViewIntent
import com.skp3214.cgpacalc.mvi.CGPACalcViewState
import com.skp3214.cgpacalc.ui.components.HeaderSection
import com.skp3214.cgpacalc.ui.components.ModernButton
import com.skp3214.cgpacalc.ui.components.ModernTextField
import com.skp3214.cgpacalc.ui.theme.AppColors
import com.skp3214.cgpacalc.ui.theme.AppDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradePointCalc(viewModel: CGPACalcViewModel = viewModel()) {
    val state = viewModel.state.value as CGPACalcViewState.Success
    val contextForToast = LocalContext.current
    var isCalculating by remember { mutableStateOf(false) }

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

    // Modern gradient background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        AppColors.Background,
                        AppColors.ContainerSecondary
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(AppDimensions.SpaceL),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Section
            HeaderSection(
                title = "Grade Point Calculator",
                subtitle = "Convert marks to grade points"
            )

            Spacer(modifier = Modifier.height(AppDimensions.SpaceXXL))

            // Result Display Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = AppDimensions.ElevationM,
                        shape = RoundedCornerShape(AppDimensions.RadiusXL)
                    ),
                shape = RoundedCornerShape(AppDimensions.RadiusXL),
                colors = CardDefaults.cardColors(
                    containerColor = AppColors.ContainerPrimary
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    AppColors.Primary.copy(alpha = 0.1f),
                                    AppColors.PrimaryLight.copy(alpha = 0.05f)
                                )
                            )
                        )
                        .padding(AppDimensions.SpaceXXL),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Grade Point",
                            style = MaterialTheme.typography.titleMedium,
                            color = AppColors.TextSecondary,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(AppDimensions.SpaceS))
                        Text(
                            text = calculatedGradePoint.ifEmpty { "Enter marks" },
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = if (calculatedGradePoint == "0") AppColors.Error else AppColors.Primary,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(AppDimensions.SpaceXXL))

            // Input Section
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = AppColors.Surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = AppDimensions.ElevationS),
                shape = RoundedCornerShape(AppDimensions.RadiusL)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppDimensions.SpaceXL),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Enter Your Marks",
                        style = MaterialTheme.typography.titleLarge,
                        color = AppColors.Primary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = AppDimensions.SpaceL)
                    )

                    ModernTextField(
                        value = marks,
                        onValueChange = { value ->
                            viewModel.processIntent(CGPACalcViewIntent.SetGrade(0, value))
                        },
                        label = "Marks (0-100)",
                        placeholder = "85",
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(AppDimensions.SpaceXL))

                    ModernButton(
                        onClick = {
                            val enteredMarks = marks.toIntOrNull()

                            if (marks.isEmpty()) {
                                Toast.makeText(contextForToast, "Please enter the marks.", Toast.LENGTH_SHORT).show()
                            } else if (enteredMarks == null) {
                                Toast.makeText(contextForToast, "Please enter numeric values for marks.", Toast.LENGTH_SHORT).show()
                            } else if (enteredMarks < 0 || enteredMarks > 100) {
                                Toast.makeText(contextForToast, "Please enter marks in the range 0-100.", Toast.LENGTH_SHORT).show()
                            } else {
                                isCalculating = true
                                CoroutineScope(Dispatchers.Main).launch {
                                    delay(500)
                                    isCalculating = false
                                    Toast.makeText(contextForToast, "Your Grade Point is: $calculatedGradePoint", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        text = "Calculate Grade Point",
                        isLoading = isCalculating,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(AppDimensions.SpaceXXL))
        }
    }
}
