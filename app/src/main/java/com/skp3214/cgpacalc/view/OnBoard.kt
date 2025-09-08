package com.skp3214.cgpacalc.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import com.skp3214.cgpacalc.R


@Composable
fun OnBoard(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFF8F8FF), Color(0xFFF57A2B))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                MyUI(navController = navController)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = RoundedCornerShape(32.dp),
                shadowElevation = 8.dp,
                tonalElevation = 4.dp,
                color = Color.White.copy(alpha = 0.95f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val imageModifier = Modifier.size(160.dp)
                    Image(
                        painter = painterResource(id = R.drawable.lpulogo),
                        contentDescription = "Lpu Logo",
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
                    Spacer(modifier = Modifier.height(32.dp))
                    ElevatedButton(
                        onClick = { navController.navigate(CGPA.route) },
                        colors = ButtonDefaults.elevatedButtonColors(
                            contentColor = Color(0xffeff6e0),
                            containerColor = Color(0xFFF57A2B)
                        ),
                        modifier = Modifier
                            .width(230.dp)
                    ) {
                        Text(text = "C.G.P.A CALCULATOR", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    ElevatedButton(
                        onClick = { navController.navigate(GradCalc.route) },
                        colors = ButtonDefaults.elevatedButtonColors(
                            contentColor = Color(0xffeff6e0),
                            containerColor = Color(0xFFF57A2B)
                        ),
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .width(230.dp)
                    ) {
                        Text(text = "GRADE", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    ElevatedButton(
                        onClick = { navController.navigate(GradePointCalc.route) },
                        colors = ButtonDefaults.elevatedButtonColors(
                            contentColor = Color(0xffeff6e0),
                            containerColor = Color(0xFFF57A2B)
                        ),
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .width(230.dp)
                    ) {
                        Text(text = "GRADE POINT", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                    ElevatedButton(
                        onClick = { navController.navigate(MinimumMark.route) },
                        colors = ButtonDefaults.elevatedButtonColors(
                            contentColor = Color(0xffeff6e0),
                            containerColor = Color(0xFFF57A2B)
                        ),
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .width(230.dp)
                    ) {
                        Text(text = "MINIMUM MARKS", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                }
            }
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
