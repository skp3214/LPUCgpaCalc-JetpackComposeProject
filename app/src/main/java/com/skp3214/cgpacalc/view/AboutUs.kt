package com.skp3214.cgpacalc.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.graphics.Brush
import com.skp3214.cgpacalc.ui.components.PremiumSectionCard

@Composable
fun AboutUs() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Brush.verticalGradient(
                colors = listOf(Color(0xFFF57A2B),Color(0xFFF8F8FF))
            ))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PremiumSectionCard(
            icon = Icons.Default.Info,
            title = "App Overview",
            content = "A modern CGPA calculator for LPU students. Calculate CGPA by marks, grade, or grade-point. Instantly view grade/grade-point for marks and minimum marks needed for a grade."
        )
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(thickness = 1.dp, color = Color(0xFFE0E0E0))
        Spacer(modifier = Modifier.height(16.dp))
        PremiumSectionCard(
            icon = Icons.Default.Person,
            title = "About the Creator",
            content = "This app is a personal project, not affiliated with any company or organization."
        )
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(thickness = 1.dp, color = Color(0xFFE0E0E0))
        Spacer(modifier = Modifier.height(16.dp))
        PremiumSectionCard(
            icon = Icons.Default.Email,
            title = "Contact",
            content = "Email: spsm1818@gmail.com"
        )
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(thickness = 1.dp, color = Color(0xFFE0E0E0))
        Spacer(modifier = Modifier.height(16.dp))
        PremiumSectionCard(
            icon = Icons.Default.Info,
            title = "Privacy & Terms",
            content = "No data is collected. No permissions required. 100% privacy."
        )
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF57A2B)),
            modifier = Modifier.shadow(4.dp, RoundedCornerShape(16.dp))
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Made with ❤️ by Sachin Prajapati",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

