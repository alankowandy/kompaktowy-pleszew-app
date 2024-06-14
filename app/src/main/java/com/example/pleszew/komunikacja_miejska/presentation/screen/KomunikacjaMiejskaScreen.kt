package com.example.pleszew.komunikacja_miejska.presentation.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pleszew.core.data.MenuItems
import com.example.pleszew.core.domain.KomunikacjaMiejskaDetails
import com.example.pleszew.core.domain.SharedViewModel
import com.example.pleszew.komunikacja_miejska.presentation.viewmodel.KomunikacjaMiejskaViewModel
import com.example.pleszew.ui.theme.Bialy
import com.example.pleszew.ui.theme.CiemnyNiebieski
import com.example.pleszew.ui.theme.JasnyNiebieski

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KomunikacjaMiejskaScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    komunikacjaMiejskaViewModel: KomunikacjaMiejskaViewModel = hiltViewModel()
) {
    sharedViewModel.setCurrentScreen(MenuItems.KomunikacjaMiejska)
    val searchText by komunikacjaMiejskaViewModel.searchText.collectAsState(initial = "")
    val isActive by komunikacjaMiejskaViewModel.isActive.collectAsState(initial = false)
    val stops = komunikacjaMiejskaViewModel.dataStops.collectAsState(initial = listOf()).value
    val searchStops by komunikacjaMiejskaViewModel.stops.collectAsState()
    val isCollected by komunikacjaMiejskaViewModel.isCollected.collectAsState(initial = false)
    val lines by komunikacjaMiejskaViewModel.lines.collectAsState(initial = listOf())
    var expandedStates by remember { mutableStateOf(mapOf<String, Boolean>()) }
    
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Card(
            backgroundColor = CiemnyNiebieski,
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(50.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "ROZKŁAD JAZDY",
                    color = Bialy,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Card(
            backgroundColor = JasnyNiebieski,
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = SearchBarDefaults.colors(
                        containerColor = Bialy
                    ),
                    query = searchText,
                    onQueryChange = {
                        komunikacjaMiejskaViewModel.updateSearchText(it)
                    },
                    onSearch = {
                        komunikacjaMiejskaViewModel.updateIsActive(false)
                    },
                    active = isActive,
                    onActiveChange = {
                        komunikacjaMiejskaViewModel.updateIsActive(it)
                    },
                    placeholder = { 
                        Text(
                            text = "Wyszukaj przystanek",
                            modifier = Modifier
                                .alpha(0.5f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    trailingIcon = {
                        if (isActive) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                modifier = Modifier
                                    .clickable {
                                        if (searchText.isNotBlank()) {
                                            komunikacjaMiejskaViewModel.updateSearchText("")
                                        } else {
                                            komunikacjaMiejskaViewModel.updateIsActive(false)
                                        }
                                    }
                            )
                        }
                    }
                ) {
                    LazyColumn() {
                        items(searchStops) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .clickable {
                                        komunikacjaMiejskaViewModel.updateSearchText(it.stopName)
                                        komunikacjaMiejskaViewModel.updateIsActive(false)
                                        komunikacjaMiejskaViewModel.getLines(it.id)
                                    }
                            ) {
                                Text(
                                    text = it.stopName,
                                    modifier = Modifier
                                        .padding(10.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
        if (isCollected) {
            LazyColumn() {
                items(lines) {stop ->
                    val isExpanded = expandedStates[stop.lineId] ?: false
                    Card(
                        backgroundColor = JasnyNiebieski,
                        elevation = 4.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Linia: ${stop.lineId}",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.h5
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(onClick = {
                                    expandedStates = expandedStates.toMutableMap().apply {
                                        this[stop.lineId] = !isExpanded
                                    }
                                }) {
                                    Icon(
                                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                        contentDescription = null
                                    )
                                }
                            }
                            if (isExpanded) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 15.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = stop.direction,
                                            style = MaterialTheme.typography.subtitle1,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 5.dp)
                                        )
                                        stop.stopsOrdered.distinct().forEach {
                                            Card(
                                                backgroundColor = Bialy,
                                                elevation = 4.dp,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(8.dp)
                                                    .clickable {
                                                        navController.navigate(
                                                            KomunikacjaMiejskaDetails.createRouteWithParam(
                                                                stopName = it,
                                                                lineId = stop.lineId
                                                            )
                                                        )
                                                    }
                                            ) {
                                                Text(
                                                    text = it,
                                                    modifier = Modifier
                                                        .padding(15.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}