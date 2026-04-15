package com.example.countries.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(onOptionSelected: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Explorateur de Pays",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Option 1 : Tous les pays
        Button(
            onClick = { onOptionSelected("all") },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Tous les pays du monde")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Option 2 : Pays d'Afrique
        Button(
            onClick = { onOptionSelected("africa") },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Pays d'Afrique")
        }
    }
}