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
fun MinimumMark(viewModel: CGPACalcViewModel = viewModel()) {
    val state = viewModel.state.value as CGPACalcViewState.Success
    val contextForToast = LocalContext.current
    var isCalculating by remember { mutableStateOf(false) }

    // Use the first grade value for single calculation
    val grade = state.gradesValues[0]
    val minimumMarks = when (grade.uppercase()) {
        "O" -> "90"
        "A+" -> "80"
        "A" -> "70"
        "B+" -> "60"
        "B" -> "50"
        "C" -> "40"
        "D" -> "34"
        "E", "R" -> "0"
        else -> if (grade.isEmpty()) "" else "Invalid Grade"
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
                title = "Minimum Marks",
                subtitle = "Find required marks for grades"
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
                            text = "Minimum Marks",
                            style = MaterialTheme.typography.titleMedium,
                            color = AppColors.TextSecondary,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(AppDimensions.SpaceS))
                        Text(
                            text = minimumMarks.ifEmpty { "Enter grade" },
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = if (minimumMarks == "Invalid Grade") AppColors.Error else AppColors.Primary,
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
                        text = "Enter Your Grade",
                        style = MaterialTheme.typography.titleLarge,
                        color = AppColors.Primary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = AppDimensions.SpaceL)
                    )

                    ModernTextField(
                        value = grade,
                        onValueChange = { value ->
                            viewModel.processIntent(CGPACalcViewIntent.SetGrade(0, value))
                        },
                        label = "Grade (O, A+, A, B+, etc.)",
                        placeholder = "O",
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(AppDimensions.SpaceXL))

                    ModernButton(
                        onClick = {
                            if (grade.isEmpty()) {
                                Toast.makeText(contextForToast, "Please enter a grade.", Toast.LENGTH_SHORT).show()
                            } else if (minimumMarks == "Invalid Grade") {
                                Toast.makeText(contextForToast, "Please enter a valid grade (O, A+, A, B+, B, C, D, E, R).", Toast.LENGTH_SHORT).show()
                            } else {
                                isCalculating = true
                                CoroutineScope(Dispatchers.Main).launch {
                                    delay(500)
                                    isCalculating = false
                                    Toast.makeText(contextForToast, "Minimum marks for $grade: $minimumMarks", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        text = "Find Minimum Marks",
                        isLoading = isCalculating,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(AppDimensions.SpaceXXL))
        }
    }
}
