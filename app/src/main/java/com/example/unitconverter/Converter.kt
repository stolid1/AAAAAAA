package com.example.unitconverter

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UnitConverter(
    navController: NavController,
    modifier: Modifier = Modifier,
){
    var inputValue by remember { mutableStateOf("0") }

    var firstUnit by remember { mutableStateOf("Select")}
    var secondUnit by remember { mutableStateOf("Select")}

    // shows the first dropdown menu
    var menuOne by remember { mutableStateOf(false)}
    var menuTwo by remember { mutableStateOf(false)}

    var firstMultiplier by remember { mutableStateOf(1.0) }
    var secondMultiplier by remember { mutableStateOf(1.0) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val resultText = remember { mutableStateOf("Result: ") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Converter",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(route = Screen.Home.route)}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(route = Screen.About.route) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Unit Converter",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontFamily = FontFamily.Monospace,
                    ),
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(64.dp))
                OutlinedTextField(
                    value = inputValue,
                    onValueChange = { inputValue = it },
                    maxLines = 1,
                    label = { Text(text = "Enter Value") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            val result = inputValue.toDoubleOrNull() ?: 0.0
                            val calculatedResult = result * firstMultiplier * secondMultiplier
                            resultText.value = "Result: $calculatedResult"
                            println("WHAT")
                            keyboardController?.hide() // Remove keyboard when pressing enter.
                        }
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box {
                        Button(onClick = { menuOne = !menuOne }) {
                            Text(text = firstUnit)
                            Icon(Icons.Default.ArrowDropDown, "")
                        }
                        DropdownMenu(
                            expanded = menuOne,
                            onDismissRequest = { menuOne = !menuOne }) {
                            DropdownMenuItem(
                                text = { Text(text = "Gram") },
                                onClick = {
                                    menuOne = !menuOne
                                    firstUnit = "Gram"
                                    firstMultiplier = 1.0
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Kilo") },
                                onClick = {
                                    menuOne = !menuOne
                                    firstUnit = "Kilo"
                                    firstMultiplier = 1000.0
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Pond") },
                                onClick = {
                                    menuOne = !menuOne
                                    firstUnit = "Pond"
                                    firstMultiplier = 453.6
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Ons") },
                                onClick = {
                                    menuOne = !menuOne
                                    firstUnit = "Ons"
                                    firstMultiplier = 28.35
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Ton") },
                                onClick = {
                                    menuOne = !menuOne
                                    firstUnit = "Ton"
                                    firstMultiplier = 1000000.0
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "To")
                    Spacer(modifier = Modifier.width(8.dp))
                    Box {
                        Button(onClick = { menuTwo = !menuTwo }) {
                            Text(text = secondUnit)
                            Icon(Icons.Default.ArrowDropDown, "")
                        }
                        DropdownMenu(
                            expanded = menuTwo,
                            onDismissRequest = { menuTwo = !menuTwo }) {
                            DropdownMenuItem(
                                text = { Text(text = "Gram") },
                                onClick = {
                                    menuTwo = !menuTwo
                                    secondUnit = "Gram"
                                    secondMultiplier = 1.0
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Kilo") },
                                onClick = {
                                    menuTwo = !menuTwo
                                    secondUnit = "Kilo"
                                    secondMultiplier = 0.001
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Pond") },
                                onClick = {
                                    menuTwo = !menuTwo
                                    secondUnit = "Pond"
                                    secondMultiplier = 0.00220462
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Ons") },
                                onClick = {
                                    menuTwo = !menuTwo
                                    secondUnit = "Ons"
                                    secondMultiplier = 0.035274
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Ton") },
                                onClick = {
                                    menuTwo = !menuTwo
                                    secondUnit = "Ton"
                                    secondMultiplier = 0.000001
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = resultText.value,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
        })
}

@Preview(showBackground = true)
@Composable
fun UnitConventorPreview() {
   UnitConverter(
       navController = rememberNavController()
   )
}

