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
import com.skp3214.cgpacalc.mvi.CGPACalcViewIntent
import com.skp3214.cgpacalc.mvi.CGPACalcViewModel
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
fun ByNumber(cgpaCalcViewModel: CGPACalcViewModel = viewModel()) {
    val state = cgpaCalcViewModel.state.value as CGPACalcViewState.Success
    val contextForToast = LocalContext.current
    var isCalculating by remember { mutableStateOf(false) }

    // Clear state and set calculation type when this screen is first loaded
    LaunchedEffect(Unit) {
        cgpaCalcViewModel.processIntent(CGPACalcViewIntent.ClearState)
        if (state.calculationType != CalculationType.ByGrade) {
            cgpaCalcViewModel.processIntent(CGPACalcViewIntent.CalculateCgpa(CalculationType.ByGrade))
        }
    }

    // Show Toast and stop loading every time calculation is triggered
    LaunchedEffect(isCalculating) {
        if (isCalculating) {
            Toast.makeText(contextForToast, "CGPA calculated successfully! Result: ${"%.2f".format(state.cgpa)}", Toast.LENGTH_LONG).show()
            isCalculating = false
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
                title = "Marks Calculator",
                subtitle = "Enter your marks and credits"
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
                            text = "Marks",
                            style = MaterialTheme.typography.titleMedium,
                            color = AppColors.Primary,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "(0 - 100)",
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
                                cgpaCalcViewModel.processIntent(CGPACalcViewIntent.SetMarks(index, value))
                            },
                            onCreditChange = { value ->
                                val credit = value.toIntOrNull() ?: 0
                                cgpaCalcViewModel.processIntent(CGPACalcViewIntent.SetCredit(index, credit))
                            },
                            gradeLabel = "Marks",
                            gradePlaceholder = "85"
                        )
                    }
                }
            }

            // Calculate Button
            ModernButton(
                onClick = {
                    isCalculating = true
                    cgpaCalcViewModel.processIntent(CGPACalcViewIntent.CalculateCgpa(CalculationType.ByNumber))
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
