package com.skp3214.cgpacalc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skp3214.cgpacalc.ui.theme.AppColors
import com.skp3214.cgpacalc.ui.theme.AppDimensions
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isError: Boolean = false
) {
    Card(
        modifier = modifier
            .shadow(
                elevation = AppDimensions.ElevationXS,
                shape = RoundedCornerShape(AppDimensions.RadiusM)
            ),
        shape = RoundedCornerShape(AppDimensions.RadiusM),
        colors = CardDefaults.cardColors(
            containerColor = if (isError) AppColors.CardBackground.copy(alpha = 0.9f) else AppColors.CardBackgroundLight
        )
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    color = AppColors.TextSecondary
                )
            },
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.TextSecondary.copy(alpha = 0.6f)
                )
            },
            modifier = Modifier.fillMaxWidth().padding(4.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppColors.Primary,
                unfocusedBorderColor = AppColors.BorderSubtle,
                errorBorderColor = AppColors.Error,
                focusedLabelColor = AppColors.Primary,
                unfocusedLabelColor = AppColors.TextSecondary,
                focusedTextColor = AppColors.TextPrimary,
                unfocusedTextColor = AppColors.TextPrimary,
                cursorColor = AppColors.Primary
            ),
            shape = RoundedCornerShape(AppDimensions.RadiusM),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            ),
            isError = isError
        )
    }
}

@Composable
fun ModernButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    Card(
        modifier = modifier
            .shadow(
                elevation = if (enabled) AppDimensions.ElevationS else 0.dp,
                shape = RoundedCornerShape(AppDimensions.RadiusL)
            ),
        shape = RoundedCornerShape(AppDimensions.RadiusL)
    ) {
        Button(
            onClick = onClick,
            enabled = enabled && !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(AppDimensions.ButtonHeight)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = if (enabled) {
                            listOf(AppColors.GradientStart, AppColors.GradientEnd)
                        } else {
                            listOf(AppColors.BorderSubtle, AppColors.BorderSubtle)
                        }
                    ),
                    shape = RoundedCornerShape(AppDimensions.RadiusL)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = AppColors.TextOnPrimary,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = AppColors.TextSecondary
            ),
            shape = RoundedCornerShape(AppDimensions.RadiusL),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = AppColors.TextOnPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                )
            }
        }
    }
}

@Composable
fun CGPADisplayCard(
    cgpa: Double,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
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
                .padding(AppDimensions.SpaceS),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your CGPA",
                    style = MaterialTheme.typography.titleMedium,
                    color = AppColors.TextSecondary,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(AppDimensions.SpaceS))
                Text(
                    text = String.format(Locale.getDefault(), "%.2f", cgpa),
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = AppColors.Primary
                )
            }
        }
    }
}

@Composable
fun HeaderSection(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall,
            color = AppColors.Primary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        subtitle?.let {
            Spacer(modifier = Modifier.height(AppDimensions.SpaceS))
            Text(
                text = it,
                style = MaterialTheme.typography.titleMedium,
                color = AppColors.TextSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SubjectInputCard(
    index: Int,
    gradeValue: String,
    creditValue: String,
    onGradeChange: (String) -> Unit,
    onCreditChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    gradeLabel: String = "Grade",
    gradePlaceholder: String = ""
){
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.CardBackgroundLight
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = AppDimensions.ElevationXS),
        shape = RoundedCornerShape(AppDimensions.RadiusM)
    ) {
        Column(
            modifier = Modifier.padding(AppDimensions.SpaceM)
        ) {
            Text(
                text = "Subject ${index + 1}",
                style = MaterialTheme.typography.labelLarge,
                color = AppColors.Primary,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = AppDimensions.SpaceS)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppDimensions.SpaceM)
            ) {
                ModernTextField(
                    value = gradeValue,
                    onValueChange = onGradeChange,
                    label = gradeLabel,
                    placeholder = gradePlaceholder,
                    modifier = Modifier.weight(0.6f)
                )

                ModernTextField(
                    value = creditValue,
                    onValueChange = onCreditChange,
                    label = "Credits",
                    placeholder = "0",
                    modifier = Modifier.weight(0.4f)
                )
            }
        }
    }
}

@Composable
fun PremiumSectionCard(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, content: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxSize()
            .shadow(2.dp, RoundedCornerShape(16.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xFFF57A2B),
                modifier = Modifier.padding(end = 12.dp)
            )
            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFFF57A2B)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = content,
                    fontSize = 15.sp,
                    color = Color(0xFF222222)
                )
            }
        }
    }
}