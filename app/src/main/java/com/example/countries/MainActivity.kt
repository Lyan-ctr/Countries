package com.example.countries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.countries.ui.theme.CountriesTheme
import com.example.countries.ui.theme.CountryListScreen
import com.example.countries.ui.theme.HomeScreen
import com.example.countries.viewmodel.CountryViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: CountryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountriesTheme {
                // État de navigation (Accueil ou Liste)
                var currentScreen by remember { mutableStateOf("home") }

                if (currentScreen == "home") {

                    // ÉCRAN D'ACCUEIL
                    HomeScreen(onOptionSelected = { choix ->
                        viewModel.loadCountries(choix)
                        currentScreen = "list"
                    })
                } else {
                    // ÉCRAN DE LISTE

                    // Gestion du bouton retour PHYSIQUE (bouton du téléphone)
                    BackHandler {
                        currentScreen = "home"
                    }

                    // On passe le viewModel et l'action de retour
                    CountryListScreen(
                        viewModel = viewModel,
                        onBack = { currentScreen = "home" }
                    )
                }
            }
        }
    }

}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CountriesTheme {
        Greeting("Android")
    }
}