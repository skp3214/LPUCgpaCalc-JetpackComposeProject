package com.skp3214.cgpacalc.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
fun ByGradePoint(cgpaCalcViewModel: CGPACalcViewModel = viewModel()) {
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
                .padding(AppDimensions.SpaceM),
            verticalArrangement = Arrangement.spacedBy(AppDimensions.SpaceM),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Section
            HeaderSection(
                title = "By GradePoint",
                subtitle = "Enter your grade points and credits"
            )

            // CGPA Display Card
            CGPADisplayCard(
                cgpa = state.cgpa,
                modifier = Modifier.padding(vertical = AppDimensions.SpaceXXS)
            )

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
                                cgpaCalcViewModel.processIntent(CGPACalcViewIntent.SetGradePoint(index, value))
                            },
                            onCreditChange = { value ->
                                val credit = value.toIntOrNull() ?: 0
                                cgpaCalcViewModel.processIntent(CGPACalcViewIntent.SetCredit(index, credit))
                            },
                            gradeLabel = "Grade Point",
                            gradePlaceholder = "8"
                        )
                    }
                }
            }

            // Calculate Button
            ModernButton(
                onClick = {
                    isCalculating = true
                    cgpaCalcViewModel.processIntent(CGPACalcViewIntent.CalculateCgpa(CalculationType.ByGradePoint))
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
