package com.rafial.invento.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class Interest(val name: String, var isSelected: Boolean = false)

val interests = listOf(
    Interest("Art"),
    Interest("Music"),
    Interest("Design"),
    Interest("Technology"),
    Interest("Sports"),
    Interest("Cooking"),
    Interest("Photography"),
    Interest("Fashion"),
    Interest("Gaming"),
    Interest("Travel")
)
//@Destination
@Composable
fun InterestScreen(navController: NavController) {
    val selectedInterests by remember { mutableStateOf(mutableStateListOf<Interest>()) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Select Your Interests",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        interests.chunked(2).forEach { rowInterests ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                rowInterests.forEach { interest ->
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = interest.isSelected,
                                onCheckedChange = { checked ->
                                    interest.isSelected = checked
                                    if (checked) {
                                        if (selectedInterests.size < 5) {
                                            selectedInterests.add(interest)
                                        } else {
                                            interest.isSelected = false
                                        }
                                    } else {
                                        selectedInterests.remove(interest)
                                    }
                                }
                            )
                            Text(
                                text = interest.name,
                                style = MaterialTheme.typography.body1,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(
            onClick = {
                // Navigasi ke halaman berikutnya di sini
                // Misalnya, navController.navigate("nextPage")
            },
            enabled = selectedInterests.isNotEmpty()
        ) {
            Text(text = "Next")
        }
    }
}

@Preview
@Composable
fun PreviewInterestScreen() {
    InterestScreen(rememberNavController())
}
