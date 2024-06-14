package com.example.pleszew.komunikacja_miejska.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pleszew.komunikacja_miejska.presentation.viewmodel.KomunikacjaMiejskaHoursViewModel
import com.example.pleszew.ui.theme.Bialy
import com.example.pleszew.ui.theme.CiemnyNiebieski

@Composable
fun KomunikacjaMiejskaHoursScreen(
    stopName: String?,
    lineId: String?,
    komunikacjaMiejskaHoursViewModel: KomunikacjaMiejskaHoursViewModel = hiltViewModel()
) {
    val selectedStop = komunikacjaMiejskaHoursViewModel.selectedStop.collectAsState(initial = listOf()).value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            backgroundColor = CiemnyNiebieski,
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            selectedStop.forEach {
                Column {
                    if (lineId != null) {
                        Text(
                            text = "Linia: $lineId",
                            color = Bialy,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        )
                    }
                    Text(
                        text = "Przystanek: ${it.stopName}",
                        color = Bialy,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    )
                }
            }
        }
        Text(
            text = "Godziny odjazdu:",
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth(),
            columns = GridCells.Adaptive(minSize = 100.dp),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(selectedStop.flatMap { it.departureTimes }) {item ->
                val formattedTime = item.take(5)
                Card(
                    backgroundColor = Bialy,
                    elevation = 4.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = formattedTime,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}