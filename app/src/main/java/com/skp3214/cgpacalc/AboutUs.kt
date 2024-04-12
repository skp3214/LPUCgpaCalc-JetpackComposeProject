package com.skp3214.cgpacalc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutUs(){
    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceAround,
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SectionHeader("App Overview")
            Text("This app is made for students of lovely professional university to calculate " +
                    "CGPA by marks, by grade or by grade-point. They can also know grade and grade-point on " +
                    "specific mark and they can know the minimum marks they need for a particular grade.")


            SectionHeader("Company/Organization Information")
            Text("This app does not belong to any company or organisation. This app is made by an individual as a personal project.")


            SectionHeader("Contact Information")
            Text("Email: spsm1818@gmail.com")


            SectionHeader("Privacy Policy and Terms of Service")
            Text("There is no privacy policy in this app because it does not collect any data from user" +
                    "neither it ask for any-type of access. It is just a simple cgpa calculator app")

            Row(modifier = Modifier.padding(top=10.dp)){
                Text(text = "Made with ❤️ by Sachin Prajapati.")
            }

        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.padding(vertical = 8.dp),
        color =  Color(0xFFF57A2B)
    )
}