package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember { mutableStateOf(false)}
            UnitConverterTheme(darkTheme = darkTheme) {
                val currentColorScheme = colorScheme // Get the current color scheme
                println(1)
                println(currentColorScheme)
                navController = rememberNavController()
                SetupNavGraph(
                    darkTheme = darkTheme,
                    onThemeUpdated = { darkTheme = !darkTheme },
                    navController = navController
                )

            }
        }
    }
}

@Composable
fun MyApp(navController: NavController, modifier: Modifier = Modifier) {
    var shouldShowHomePage by rememberSaveable { mutableStateOf(true) }
    Surface(modifier.background(colorScheme.surface)) {
        if (shouldShowHomePage) {
            homePage(onContinueClicked = { shouldShowHomePage = false })
        } else {
            Column {
                UnitConverter(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController)
            }
        }
    }
}


@Composable
fun homePage(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //Spacer(modifier = Modifier.height(300.dp))*/
        Text(
            text = "Mobile App Development Keuzedeel",
            fontWeight = FontWeight.Bold
        )
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
    // Footer
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = modifier.padding(10.dp),
            text = "@Vasilios Mastoros 2023",
            color = Color.Black.copy(alpha = 0.5f)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    UnitConverterTheme {
        homePage(onContinueClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverter(
            navController = rememberNavController()
        )
    }
}