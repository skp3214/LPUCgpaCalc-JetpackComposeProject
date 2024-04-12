package com.skp3214.cgpacalc


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun OnBoard(navController: NavController) {
    MyUI(navController=navController)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        val imageModifier = Modifier.size(200.dp)

        Image(
            painter = painterResource(id = R.drawable.lpulogo),
            contentDescription ="Lpu Logo",
            contentScale = ContentScale.Fit,
            modifier = imageModifier
        )

        Text(
            text = "C.G.P.A",
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold,
            style = TextStyle(fontSize = 44.sp),
            color = Color(0xffe85d04),
            modifier = Modifier.padding(top = 30.dp)
        )

        Text(
            text = "Calculator ",
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 54.sp),
            color = Color(0xffe85d04),
            modifier = Modifier.padding(top = 10.dp)
        )

        ElevatedButton(
            onClick = { navController.navigate(CGPA.route) },
            colors = ButtonDefaults.elevatedButtonColors(
                contentColor = Color(0xffeff6e0),
                containerColor = Color(0xFFF57A2B)
            ),
            modifier = Modifier
                .padding(top = 50.dp)
                .width(190.dp)
                .fillMaxWidth()
        ) {
            Text(text = "C.G.P.A (Semester)")
        }

        ElevatedButton(
            onClick = { navController.navigate(GradCalc.route) },
            colors = ButtonDefaults.elevatedButtonColors(
                contentColor = Color(0xffeff6e0),
                containerColor = Color(0xFFF57A2B)
            ),
            modifier = Modifier
                .padding(top = 20.dp)
                .width(190.dp)
        ) {
            Text(text = "GRADE")
        }

        ElevatedButton(
            onClick = { navController.navigate(GradePointCalc.route) },
            colors = ButtonDefaults.elevatedButtonColors(
                contentColor = Color(0xffeff6e0),
                containerColor = Color(0xFFF57A2B)
            ),
            modifier = Modifier
                .padding(top = 20.dp)
                .width(190.dp)
        ) {
            Text(text = "GRADE POINT")
        }

        ElevatedButton(
            onClick = { navController.navigate(MinimumMark.route) },
            colors = ButtonDefaults.elevatedButtonColors(
                contentColor = Color(0xffeff6e0),
                containerColor = Color(0xFFF57A2B)
            ),
            modifier = Modifier
                .padding(top = 20.dp)
                .width(190.dp)
        ) {
            Text(text = "Minimum Marks")
        }
    }
}
@Composable
fun MyUI(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    LocalContext.current.applicationContext

    Box(
        modifier = Modifier

            .padding(end=10.dp)
            .wrapContentSize(align = Alignment.TopEnd),
        contentAlignment = Alignment.TopEnd
    ) {
        // vertical 3 dots icon
        IconButton(
            onClick = {
                expanded = true
            }
        ) {
            Icon(Icons.Outlined.MoreVert, contentDescription = "Open Menu")
        }

        // menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0xFFFCDFB4))
        ) {
            // menu items
            DropdownMenuItem(
                text = {
                    Text("About Us",color=Color(0xFFF57A2B))
                },
                onClick = {
                    navController.navigate(AboutUs.route)
                    expanded = false
                },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Info,
                        contentDescription = null,

                    )
                }
            )
        }
    }
}
