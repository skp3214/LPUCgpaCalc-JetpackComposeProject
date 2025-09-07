package com.skp3214.cgpacalc.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.skp3214.cgpacalc.mvi.CGPACalcViewModel
import com.skp3214.cgpacalc.mvi.CGPACalcViewIntent
import com.skp3214.cgpacalc.mvi.CGPACalcViewState
import com.skp3214.cgpacalc.ui.components.CGPADisplayCard
import com.skp3214.cgpacalc.ui.components.HeaderSection
import com.skp3214.cgpacalc.ui.components.ModernButton
import com.skp3214.cgpacalc.ui.components.SubjectInputCard
import com.skp3214.cgpacalc.ui.theme.AppColors
import com.skp3214.cgpacalc.ui.theme.AppDimensions
import com.skp3214.cgpacalc.utils.CalculationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ByGradePoint(viewModel: CGPACalcViewModel = viewModel()) {
    val state = viewModel.state.value as CGPACalcViewState.Success
    val contextForToast = LocalContext.current
    var isCalculating by remember { mutableStateOf(false) }

    // Ensure we're in ByGradePoint mode and clear state from other screens
    LaunchedEffect(Unit) {
        viewModel.processIntent(CGPACalcViewIntent.ClearState)
        if (state.calculationType != CalculationType.ByGradePoint) {
            viewModel.processIntent(CGPACalcViewIntent.CalculateCgpa(CalculationType.ByGradePoint))
        }
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
            verticalArrangement = Arrangement.spacedBy(AppDimensions.SpaceL),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Section
            HeaderSection(
                title = "Grade Point Calculator",
                subtitle = "Enter your grade points and credits"
            )

            // CGPA Display Card
            CGPADisplayCard(
                cgpa = state.cgpa,
                modifier = Modifier.padding(vertical = AppDimensions.SpaceM)
            )

            // Input Instructions Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = AppColors.ContainerPrimary
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = AppDimensions.ElevationXS)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppDimensions.SpaceL),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Grade Points",
                            style = MaterialTheme.typography.titleMedium,
                            color = AppColors.Primary,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "(0.0 - 10.0)",
                            style = MaterialTheme.typography.bodySmall,
                            color = AppColors.TextSecondary
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier
                            .height(40.dp)
                            .width(1.dp),
                        thickness = DividerDefaults.Thickness, color = AppColors.BorderSubtle
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Credits",
                            style = MaterialTheme.typography.titleMedium,
                            color = AppColors.Primary,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "(1, 2, 3, etc.)",
                            style = MaterialTheme.typography.bodySmall,
                            color = AppColors.TextSecondary
                        )
                    }
                }
            }

            // Subject Input Fields
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = AppColors.Surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = AppDimensions.ElevationS)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(AppDimensions.SpaceL),
                    verticalArrangement = Arrangement.spacedBy(AppDimensions.SpaceM)
                ) {
                    items(state.gradesValues.size) { index ->
                        SubjectInputCard(
                            index = index,
                            gradeValue = state.gradesValues[index],
                            creditValue = state.creditValues[index]?.toString() ?: "",
                            onGradeChange = { value ->
                                viewModel.processIntent(CGPACalcViewIntent.SetGrade(index, value))
                            },
                            onCreditChange = { value ->
                                val credit = value.toIntOrNull() ?: 0
                                viewModel.processIntent(CGPACalcViewIntent.SetCredit(index, credit))
                            },
                            gradeLabel = "Grade Point",
                            gradePlaceholder = "8.5"
                        )
                    }
                }
            }

            // Calculate Button
            ModernButton(
                onClick = {
                    // Validation logic
                    var isValid = true
                    var errorMessage = ""

                    for (i in state.gradesValues.indices) {
                        val gradePoint = state.gradesValues[i]
                        val credit = state.creditValues[i]

                        if (gradePoint.isNotBlank()) {
                            val gradePointDouble = gradePoint.toDoubleOrNull()
                            if (gradePointDouble == null || gradePointDouble !in 0.0..10.0) {
                                isValid = false
                                errorMessage = "Enter valid grade points (0-10) for Subject ${i + 1}"
                                break
                            }
                        }

                        if (credit != null && credit < 0) {
                            isValid = false
                            errorMessage = "Enter valid credit for Subject ${i + 1}"
                            break
                        }
                    }

                    if (isValid) {
                        isCalculating = true
                        viewModel.processIntent(CGPACalcViewIntent.CalculateCgpa(CalculationType.ByGradePoint))

                        CoroutineScope(Dispatchers.Main).launch {
                            delay(800)
                            isCalculating = false
                            Toast.makeText(contextForToast, "Your CGPA: ${"%.2f".format(state.cgpa)}", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(contextForToast, errorMessage, Toast.LENGTH_LONG).show()
                    }
                },
                text = "Calculate CGPA",
                isLoading = isCalculating,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = AppDimensions.SpaceM)
            )

            // Bottom spacing for better UX
            Spacer(modifier = Modifier.height(AppDimensions.SpaceXL))
        }
    }
}
